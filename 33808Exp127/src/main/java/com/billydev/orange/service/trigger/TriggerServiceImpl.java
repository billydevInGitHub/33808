package com.billydev.orange.service.trigger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billydev.blib.dao.DesigntimeApplRepository;
import com.billydev.blib.dao.EventInfoRepository;
import com.billydev.blib.dao.RuntimeApplRepository;
import com.billydev.blib.dao.RuntimeJobRepository;
import com.billydev.blib.jobengine.RuntimeApplicationProcessor;
import com.billydev.blib.entity.EventInfo;
import com.billydev.blib.entity.DTAppInfo;
import com.billydev.blib.entity.DTJobInfo;
import com.billydev.blib.model.RT_Appl_Info;
import com.billydev.blib.entity.RTJobInfo;
import com.billydev.blib.entity.RTAppInfo;



@Service("triggerService")
public class TriggerServiceImpl implements TriggerService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	@Autowired 
	private RuntimeApplicationProcessor runtimeApplicationProcessor; 
	
	static{
		//users= populateDummyUsers();
	}


    @Autowired
    private DesigntimeApplRepository dtApplRepository;


    @Autowired
    private RuntimeApplRepository rtApplRepository;  
    
    @Autowired
    private RuntimeJobRepository rtJobRepository;  

    @Autowired
    private EventInfoRepository eventInfoRepository;  
    
	@Override
	@Transactional 
	public RT_Appl_Info trigger_application(EventInfo event_info) {
		/*
		 * the method could be used for both manually trigger or scheduled trigger
		 * 
		 * 1.use DT tables to generate a new RT appl and  RT jobs
		 * 2.update the unique id of the generation number 
		 * 3 Once DB side done, do the overall check and sent Http message to core engine 
		 *     message should be the next run job id etc.  
		 * 
		 */
		

		
		RTAppInfo runtime_appl_info = new RTAppInfo();
		
		/*
		 * the following trigger stuff need to be "enhanced" with real trigger meaning !!
		 * so far just hardcode here
		 * todo: need to use enscript to replace the hardcode stuff here!! 
		 */
		runtime_appl_info.setRtApplicationName(event_info.getDtAppname());
		runtime_appl_info.setDtApplicationName(event_info.getDtAppname());
		
		/*
		 * get the DT application (include dt jobs) from DAO using the DT application name in the event!! 
		 */
		
		DTAppInfo dt_appl_info = dtApplRepository.findByDTAppName(event_info.getDtAppname());
		
//		Set<DTJobInfo> list_DT_Job_Info=dt_appl_info.getDTJobs();
		Set<DTJobInfo> list_DT_Job_Info=new HashSet<>();
		Set<RTJobInfo> list_RT_Job_Info= new HashSet<>();
		
		
		
		/*
		 * insert rt application with return id ,then sync frontend object with db 
		 */
		long rtAppId=rtApplRepository.save(runtime_appl_info).getAppId();
		
		
		
		RTJobInfo rt_job_info= null;
		

		for( DTJobInfo dt_job_info  : list_DT_Job_Info   ) {
			/*
			 * dt job info becomes rt job info need to run the enscript 
			 * todo: here just temp hard coded target IP address!
			 */
			rt_job_info= new RTJobInfo();
			rt_job_info.setRtApplicationName(dt_job_info.getDTAppName());
			rt_job_info.setJobType(dt_job_info.getJobType());
			rt_job_info.setQualifier(dt_job_info.getQualifier());
			rt_job_info.setPredecessorNames(dt_job_info.getPredecessorNames());
			rt_job_info.setJobName(dt_job_info.getDTJobName());
			rt_job_info.setAgentName(dt_job_info.getAgentName());
			rt_job_info.setTarget("job-agent-linux");//todo: the trigger procedure need resolve from agentName !!
			rt_job_info.setPort(9898);
			rt_job_info.setScript(dt_job_info.getScript());  //todo: need to resolve the script name stuff
			rt_job_info.setState("01"); //todo: temp hardcode to wait state
			rt_job_info.setApplGenerationNumber(runtime_appl_info.getApplGenerationNumber());
			list_RT_Job_Info.add(rt_job_info); 
		}
		


		
		
		runtime_appl_info.setJobs(list_RT_Job_Info);	
		

		RuntimeApplicationProcessor.setupPrdecessorSuccessorListByName(list_RT_Job_Info);	

		/*
		 * then need to insert rt_job_info with return id 
		 */
		
		for(RTJobInfo rt_job_info_updateDb:runtime_appl_info.getJobs()) {
			rtJobRepository.save(rt_job_info_updateDb);
		}

		
		
		
		/* 
		 * 
		 * update RT_appl_info and RT_job_info at the same time  
		 * this is big operation, but no need to sync the threads 
		 * in core engine
		 * need to call overallCheckAndProcess in RuntimeApplicationProcessor
		 */
		
		try {				
			runtimeApplicationProcessor.overallCheckAndProcess(runtime_appl_info);
		}catch(Exception e) {
			System.out.println("TriggerServiceImpl: caught exception ... making all jobs as agent down..."+e.toString());
			//e.printStackTrace();
		}
		
	RT_Appl_Info returnSimpleRtApplInfo= new RT_Appl_Info(); 
		returnSimpleRtApplInfo.setAppl_id(rtAppId);
		returnSimpleRtApplInfo.setApplication_name(runtime_appl_info.getRtApplicationName());
		returnSimpleRtApplInfo.setGenerationNumber(runtime_appl_info.getApplGenerationNumber());
//
		return returnSimpleRtApplInfo; 

	}


	@Override

	public List<DTAppInfo> listAllDesignTimeAppls() {
		return dtApplRepository.findAll();
	}


	@Override
	public DTAppInfo createDesignTimeAppl(DTAppInfo dtAppInfo) {
		return dtApplRepository.save(dtAppInfo);
		
	}
	
	
	@Override
	public DTAppInfo updateDesignTimeAppl(DTAppInfo dtAppInfo) {
		return dtApplRepository.save(dtAppInfo);
		
	}
	
	
	@Override
	public void deleteDesignTimeAppl(long appl_id) {
		dtApplRepository.deleteById(appl_id);
	}


	@Override
	public List<EventInfo> getAllEvents() {
		
		return eventInfoRepository.findAll();
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
