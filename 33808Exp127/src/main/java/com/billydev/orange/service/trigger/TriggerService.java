package com.billydev.orange.service.trigger;


import java.util.ArrayList;
import java.util.List;

import com.billydev.blib.entity.EventInfo;
import com.billydev.blib.model.DT_Appl_Info;
import com.billydev.blib.model.RT_Appl_Info;


public interface TriggerService {
	

	
	Boolean createDesignTimeAppl(DT_Appl_Info dtApplInfo);
	
	ArrayList<DT_Appl_Info> listAllDesignTimeAppls();
	


	DT_Appl_Info updateDesignTimeAppl(DT_Appl_Info dtApplInfo);

	Boolean deleteDesignTimeAppl(long appl_id);

	RT_Appl_Info trigger_application(EventInfo event_info);

	List<EventInfo> getAllEvents();
	

	
}
