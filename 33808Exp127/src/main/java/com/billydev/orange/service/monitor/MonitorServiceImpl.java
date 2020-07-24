package com.billydev.orange.service.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billydev.blib.common.CommonConfiguration;
import com.billydev.blib.common.CommonMsgInQueue;
import com.billydev.blib.common.CommonUtils;
import com.billydev.blib.dao.RuntimeApplRepository;
import com.billydev.blib.dao.RuntimeJobRepository;
import com.billydev.blib.jobengine.RuntimeApplicationProcessor;
import com.billydev.blib.model.RT_Job_Info;
import com.billydev.blib.model.Runtime_Appl_Info;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service("monitorService")
public class MonitorServiceImpl implements MonitorService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	@Autowired 
	private RuntimeApplicationProcessor runtimeApplicationProcessor; 
	
	static{
		//users= populateDummyUsers();
	}

//    @Autowired
//    DataSource dataSource;

    @Autowired
    private RuntimeJobRepository rtJobRepository;

    @Autowired
    private RuntimeApplRepository rtApplRepository;


    
//	public Runtime_Appl_Info get_Runtime_Appl_info_By_Rt_Job_Info(RT_Job_Info rtJobInfo) {
//
//		/*
//		 * todo: this service is kind of temp one
//		 */
//		Runtime_Appl_Info rtai = rtApplRepository.get_Runtime_Appl_info_By_RT_Job_Id(rtJobInfo.getJob_id()); 
//
//		return rtai;
//	}


	public Runtime_Appl_Info get_Runtime_Appl_info(long appl_id) {

		/*
		 * todo: this service is kind of temp one
		 */
		Runtime_Appl_Info rtai = rtApplRepository.get_Runtime_Appl_info(appl_id); 

		return rtai;
	}
	
	public List<RT_Job_Info> findAllJobs() {

		
		List<RT_Job_Info> list = rtJobRepository.findAll();
//		List<User> users = new ArrayList<User>();
//		for ( Customer c: list){
//			User u = new User(c.getId(),c.getName(),40,100000);
//			users.add(u);
//			System.out.println(u.getId()+":"+u.getName());
//		}

		return list;
	}


	@Override
	@Transactional 
	public Boolean updateFromClient(CommonMsgInQueue msgInQueue) {
		/*
		 * 1 Got the message from client
		 * 2 update database using @Transactional implementation 
		 * 3 Do the overall check of the application state change
		 * 4 Send out the new message to engine
		 */
		RT_Job_Info rt_job_info=null; 
		
		switch(msgInQueue.getMsgType()) {
		case CommonConfiguration.MSG_JOB_CANCELLED:
			break; 
		case CommonConfiguration.MSG_JOB_COMPLETED:
			/*
			 * update rt_job_info then do the overall check
			 */
			rt_job_info=rtJobRepository.getRTJobInfo(msgInQueue.getJobId()); 
			rt_job_info.setState(CommonConfiguration.JOB_STATE_COMPLETED);
			rtJobRepository.setRTJobState(rt_job_info, CommonConfiguration.JOB_STATE_COMPLETED); 
			break;
		case CommonConfiguration.MSG_JOB_ACTION_RELEASE_A_HELD_JOB:
			rt_job_info=rtJobRepository.getRTJobInfo(msgInQueue.getJobId()); 
			rt_job_info.setState(CommonConfiguration.JOB_STATE_WAIT); //just set it back to wait
			rtJobRepository.setRTJobState(rt_job_info,CommonConfiguration.JOB_STATE_WAIT); 
		}
		
		/*
		 * after state update, we need to do the overall state transform of the application
		 */
		Runtime_Appl_Info rtApplInfo=rtApplRepository.get_Runtime_Appl_info_By_RT_Job_Id(rt_job_info.getJob_id());
		
		/*
		 * we got the predecessor names setup, but predecessor list not setup
		 * 
		 */
		
		RuntimeApplicationProcessor.setupPrdecessorSuccessorListByName(rtApplInfo.getJobs());
		
		
		return runtimeApplicationProcessor.overallCheckAndProcess(rtApplInfo); 

	}

	@Override
	public RT_Job_Info updateRTJobInfo(RT_Job_Info rtJobInfo) {
		
		//update rt job in database
		//todo: we might not even need to update the database when we send msg to core engine
		//the core engine dequeued msg will send back to web controller, then we can update the db
		RT_Job_Info rt_Job_Info=rtJobRepository.updateRTJobInfo(rtJobInfo); 
		
		
		//only when rtJobInfo.state=="02" we need send out the MSG_JOB_ACTION_RELEASE_A_HELD_JOB
		//todo: need to remove the following hard coding
		if(rtJobInfo.getState()=="01") {
		Socket socket=null;
		try {
			socket = new Socket(CommonConfiguration.SERVER_ADDRESS, CommonConfiguration.SERVER_LISTEN_PORT);

		 
//		 System.out.println("MonitorServiceImpl : sending msg to engine, Msg is:"
//	        		+CommonUtils.getMsgInfo(msgInQueue)+" result is:"+result);	
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		ObjectMapper mapper=new ObjectMapper(); 
		CommonMsgInQueue msgInQueue=new CommonMsgInQueue(); 
		
		msgInQueue.setMsgType(CommonConfiguration.MSG_JOB_ACTION_RELEASE_A_HELD_JOB);
		msgInQueue.setJobName(rtJobInfo.getJob_name());
		msgInQueue.setTarget(CommonConfiguration.CLIENT_MACHINE_ADDRESS);//todo: we just hard code here in database, we can use agentname field
		msgInQueue.setTargetPort(CommonConfiguration.CLIENT_LISTEN_PORT);//todo: we need to get this from db as well
		msgInQueue.setJobScript(rtJobInfo.getScript());
		msgInQueue.setJobId(rtJobInfo.getJob_id());
		
		

			out.println(mapper.writeValueAsString(msgInQueue));

        System.out.println("RuntimeApplicationProcessor : msg sent to job engine, Msg is:"
        		+CommonUtils.getMsgInfo(msgInQueue)+" result is:"+"OK no exception");	
        
		} catch (Exception e) {

	        throw new RuntimeException("MonitorServiceImpl: error send result to core engine!"); 
	    } finally {
	        try {
	        	if(socket!=null) {
				socket.close();
	        	}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		}
		
		return rt_Job_Info;
	}
	
	
	/*
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		Address address1 =new Address("123","Calgary","Alberta","Canada");
		Address address2 = new Address("678", "Vancouver", "BC", "Canada");
		User user1=new User(counter.incrementAndGet(),"Sam",30, 70000);
			user1.setAddress(address1);
			String[] jobTitles={"Programmer", "Manager"};
			user1.setJobTitles(jobTitles);
		User user2=new User(counter.incrementAndGet(),"Tom",40, 50000);
			jobTitles=new String[]{"Programmer", "Accountant"};
		    user2.setAddress(address1);
		    user2.setJobTitles(jobTitles);
		User user3=new User(counter.incrementAndGet(),"Jerome",45, 30000);
			jobTitles=new String[]{"VP", "Accountant"};
		     user3.setAddress(address2);
		     user3.setJobTitles(jobTitles);
		User user4=new User(counter.incrementAndGet(),"Silvia",50, 40000);
			 jobTitles=new String[]{"VP", "HR"};
		     user4.setAddress(address2);
		     user4.setJobTitles(jobTitles);
		     
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		return users;
	}
 */
}
