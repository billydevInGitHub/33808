package com.billydev.orange.service.trigger;


import java.util.ArrayList;
import java.util.List;

import com.billydev.blib.entity.EventInfo;
import com.billydev.blib.entity.DTAppInfo;
import com.billydev.blib.model.RT_Appl_Info;


public interface TriggerService {



	DTAppInfo createDesignTimeAppl(DTAppInfo dtAppInfo);
	
	List<DTAppInfo> listAllDesignTimeAppls();
	


	DTAppInfo updateDesignTimeAppl(DTAppInfo dtAppInfo);

	void deleteDesignTimeAppl(long appl_id);

	RT_Appl_Info trigger_application(EventInfo event_info);

	List<EventInfo> getAllEvents();
	

	
}
