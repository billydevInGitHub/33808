package com.billydev.orange.service.monitor;


import java.util.List;

import com.billydev.blib.common.CommonMsgInQueue;
import com.billydev.blib.model.RT_Job_Info;
import com.billydev.blib.model.Runtime_Appl_Info;

public interface MonitorService {
	
//	User findById(long id);
//	
//	User findByName(String name);
//	
//	void saveUser(User user);
//	
//	void updateUser(User user);
//	
//	void deleteUserById(long id);

	List<RT_Job_Info> findAllJobs();
	
	Runtime_Appl_Info get_Runtime_Appl_info(long appl_id);  
	
	Boolean updateFromClient(CommonMsgInQueue msgInQueue);

	RT_Job_Info updateRTJobInfo(RT_Job_Info rtJobInfo); 
	
//	void deleteAllUsers();
//	
//	boolean isUserExist(User user);
	
}
