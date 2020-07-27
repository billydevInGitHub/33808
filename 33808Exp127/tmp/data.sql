delete from dtjobinfo

delete from dtappinfo

delete from eventinfo


insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (2,"Event_Daily_Vendor_Process", "Daily_Vendor_Process",           "Alex",              now() , now(),"22pm everyday","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (3,"Event_Monthly_GL_Report", "Monthly_GL_Report",           "Billy",              now() , now(),"6pm first day of everyday month","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (4,"Event_Customer_Suvey_Report", "Customer_Suvey_Report",           "Billy",              now() , now(),"manually trigger","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (5,"Event_Daily_Payment_Receive_Preprocessing", "Daily_Payment_Receive_Preprocessing",           "Billy",              now() , now(),"21pm everyday","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (6,"Event_Tax_GST_PST_Info_Update", "Tax_GST_PST_Info_Update",           "Billy",              now() , now(),"manually triger","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (7,"Event_Daily_Database_Backup", "Daily_Database_Backup",           "Billy",              now() , now(),"22pm everyday","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (8,"Event_Daily_Usage_File_Backup", "Daily_Usage_File_Backup",           "Billy",              now() , now(),"22pm everyday","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");

insert into  eventinfo(eventId,eventName,                       dtAppname,         eventCreatorUserId, eventCreateTime, nextScheduledTime,schedule,state,userParameters,description)
                values (9,"Event_Daylight_Saving_Time_Switch", "Daylight_Saving_Time_Switch",           "Billy",              now() , now(),"November 3, 2019, 2:00:00 am ","Ready","i-0ce89e7d755578ce5, t2.micro, Production","this is test application");




insert  into dtappinfo (appId,DTAppName,uploadTime,appCreateUserId)
                   values (1,"data_88010_U001_Diagram_c", now(),"Billy");
insert  into dtappinfo (appId,DTAppName,uploadTime,appCreateUserId)
                   values (2,"data_88010_U001_Diagram_c_windows", now(),"Billy");
insert  into dtappinfo (appId,DTAppName,uploadTime,appCreateUserId)
                   values (3,"Online_Shop_TOM_AAA_Daily_Billing_Invoicing", now(),"Billy");

insert into dtjobinfo ( jobId, dtAppId, jobType, dtJobName, qualifier,dtAppName, predecessorNames, predecessorCondition,successorNames,    state,  agentName,     script,    argumentsOfScript, scriptUserId)
				values  (1, 1, "Linux",  "Linux_start","",     "data_88010_U001_Diagram_c",  "",		             "",                   "Linux_job1,Linux_job2,Linux_job4",               "",  "job-agent-linux:9898", "ls",  "",             "billy");

insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (2,1,"Linux",  "Linux_job4", "",       "data_88010_U001_Diagram_c", "Linux_start",	          "",                  "Linux_job5",                            "",                 "job-agent-linux:9898", "ls", "",                     "billy");
insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (3,1, "Linux", "Linux_job1", "",       "data_88010_U001_Diagram_c", "Linux_start",	          "",                  "Linux_job3",                            "",                 "job-agent-linux:9898", "ls", "",                 "billy");
insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (4,1, "Linux", "Linux_job2", "",       "data_88010_U001_Diagram_c", "Linux_start",	          "",                  "Linux_job3",                            "",                 "job-agent-linux:9898", "ls", "",                 "billy");
insert into dtjobinfo (jobId, dtAppId,   jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (5,1, "Linux", "Linux_job5", "",       "data_88010_U001_Diagram_c", "Linux_job4",	          "",                  "Linux_end",                            "",                 "job-agent-linux:9898", "ls", "",                  "billy");
insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (6,1, "Linux", "Linux_job3", "",       "data_88010_U001_Diagram_c", "Linux_job1,Linux_job2",	      "",         "Linux_end",                            "",                 "job-agent-linux:9898", "ls", "",                  "billy");
insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (7,1, "Linux", "Linux_end", "",        "data_88010_U001_Diagram_c", "Linux_job3,Linux_job5",	      "",             "",                                "",                 "job-agent-linux:9898", "ls", "",                 "billy");



insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (8,2, "Windows",  "Windows_start","",     "data_88010_U001_Diagram_c_windows",  "",		             "",                   "Windows_job1,Windows_job2,Windows_job4",                  "",  "localhost:9898", "ping -n 11  localhostWrong ",  "",             "billy");

insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (9,2,"Windows",  "Windows_job4", "",       "data_88010_U001_Diagram_c_windows", "Windows_start",	          "",                  "Windows_job5",                            "",                 "localhost:9898", "ping -n 11  localhostWrong ", "",                     "billy");

insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (10,2, "Windows", "Windows_job1", "",       "data_88010_U001_Diagram_c_windows", "Windows_start",	          "",                  "Windows_job3",                            "",                 "localhost:9898", "ping -n 11  localhostWrong ", "",                 "billy");
insert into dtjobinfo (jobId, dtAppId,  jobType, dtJobName, qualifier,dtAppName,         predecessorNames,     predecessorCondition,successorNames,    state,  agentName,     script,               argumentsOfScript, scriptUserId)
				values  (11,2, "Windows", "Windows_job2", "",       "data_88010_U001_Diagram_c_windows", "Windows_start",	          "",                  "Windows_job3",                            "",                 "localhost:9898", "ping -n 11  localhostWrong ", "",                 "billy");


