package com.billydev.orange.service.monitor;


import java.util.List;

import com.billydev.blib.common.CommonMsgInQueue;
import com.billydev.blib.entity.RTJobInfo;
import com.billydev.blib.entity.RuntimeApplInfo;

public interface MonitorService {



	List<RTJobInfo> findAllJobs();

	RuntimeApplInfo get_Runtime_Appl_info(long appl_id);

	Boolean updateFromClient(CommonMsgInQueue msgInQueue);

	RTJobInfo updateRTJobInfo(RTJobInfo rtJobInfo);



}
