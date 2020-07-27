package com.billydev.orange.service.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.billydev.blib.dao.RuntimeApplRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billydev.blib.common.CommonConfiguration;
import com.billydev.blib.common.CommonMsgInQueue;
import com.billydev.blib.common.CommonUtils;
//import com.billydev.blib.dao.RuntimeApplRepository;
import com.billydev.blib.dao.RuntimeJobRepository;
import com.billydev.blib.jobengine.RuntimeApplicationProcessor;
import com.billydev.blib.entity.RTJobInfo;
import com.billydev.blib.entity.RTAppInfo;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service("monitorService")
public class MonitorServiceImpl implements MonitorService{

	private static final AtomicLong counter = new AtomicLong();

	@Autowired
	private RuntimeApplicationProcessor runtimeApplicationProcessor;

	static{
		//users= populateDummyUsers();
	}


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


	public RTAppInfo get_Runtime_Appl_info(long appl_id) {

		/*
		 * todo: this service is kind of temp one
		 */
		RTAppInfo rtai = rtApplRepository.getOne(appl_id);

		return rtai;
	}

	public List<RTJobInfo> findAllJobs() {


		List<RTJobInfo> list = rtJobRepository.findAll();

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
		RTJobInfo rt_job_info=null;

		switch(msgInQueue.getMsgType()) {
		case CommonConfiguration.MSG_JOB_CANCELLED:
			break;
		case CommonConfiguration.MSG_JOB_COMPLETED:
			/*
			 * update rt_job_info then do the overall check
			 */
			rt_job_info=rtJobRepository.getOne(msgInQueue.getJobId());
			rt_job_info.setState(CommonConfiguration.JOB_STATE_COMPLETED);
			rtJobRepository.save(rt_job_info);
			break;
		case CommonConfiguration.MSG_JOB_ACTION_RELEASE_A_HELD_JOB:
			rt_job_info=rtJobRepository.getOne(msgInQueue.getJobId());
			rt_job_info.setState(CommonConfiguration.JOB_STATE_WAIT); //just set it back to wait
			rtJobRepository.save(rt_job_info);
		}

		/*
		 * after state update, we need to do the overall state transform of the application
		 */
		RTAppInfo rtApplInfo=rtApplRepository.getOne(rt_job_info.getJobId());

		/*
		 * we got the predecessor names setup, but predecessor list not setup
		 *
		 */

		RuntimeApplicationProcessor.setupPrdecessorSuccessorListByName(rtApplInfo.getJobs());


		return runtimeApplicationProcessor.overallCheckAndProcess(rtApplInfo);

	}

	@Override
	public RTJobInfo updateRTJobInfo(RTJobInfo rtJobInfo) {

		//update rt job in database
		//todo: we might not even need to update the database when we send msg to core engine
		//the core engine dequeued msg will send back to web controller, then we can update the db
		RTJobInfo rt_Job_Info=rtJobRepository.save(rtJobInfo);


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
		msgInQueue.setJobName(rtJobInfo.getJobName());
		msgInQueue.setTarget(CommonConfiguration.CLIENT_MACHINE_ADDRESS);//todo: we just hard code here in database, we can use agentname field
		msgInQueue.setTargetPort(CommonConfiguration.CLIENT_LISTEN_PORT);//todo: we need to get this from db as well
		msgInQueue.setJobScript(rtJobInfo.getScript());
		msgInQueue.setJobId(rtJobInfo.getJobId());



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


}
