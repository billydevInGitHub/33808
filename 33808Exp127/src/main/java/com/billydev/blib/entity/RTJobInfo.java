package com.billydev.blib.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Table(name = "RTJobInfo")
public class RTJobInfo {
	long jobId;
	String jobName;
	String qualifier;
	String userId;
	String jobType;
	String predecessorNames;
	String target;
	int port;



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "rtappl_id", referencedColumnName = "rtappl_id")
	})
//	RuntimeApplInfo runtimeApplInfo;

	long rtappl_id;

	public long getRtappl_id() {
		return rtappl_id;
	}

	public void setRtappl_id(long rtappl_id) {
		this.rtappl_id = rtappl_id;
	}

	public long getRtapp_id() {
		return rtappl_id;
	}

	public void setRtapp_id(long rtapp_id) {
		this.rtappl_id = rtapp_id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getPredecessorNames() {
		return predecessorNames;
	}

	public void setPredecessorNames(String predecessorNames) {
		this.predecessorNames = predecessorNames;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getArgumentsOfScript() {
		return argumentsOfScript;
	}

	public void setArgumentsOfScript(String argumentsOfScript) {
		this.argumentsOfScript = argumentsOfScript;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getSuccessorNames() {
		return successorNames;
	}

	public void setSuccessorNames(String successorNames) {
		this.successorNames = successorNames;
	}


	public ArrayList<RTJobInfo> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(ArrayList<RTJobInfo> predecessors) {
		this.predecessors = predecessors;
	}

	public ArrayList<RTJobInfo> getSuccessors() {
		return successors;
	}

	public void setSuccessors(ArrayList<RTJobInfo> successors) {
		this.successors = successors;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	Timestamp startTime;
	String returnCode;
	String agentName;
	String argumentsOfScript;
	String script;
	String state;
	String rtApplicationName;
	String successorNames;
	long applGenerationNumber;

	public String getRtApplicationName() {
		return rtApplicationName;
	}

	public void setRtApplicationName(String rtApplicationName) {
		this.rtApplicationName = rtApplicationName;
	}

	public long getApplGenerationNumber() {
		return applGenerationNumber;
	}

	public void setApplGenerationNumber(long applGenerationNumber) {
		this.applGenerationNumber = applGenerationNumber;
	}

	ArrayList<RTJobInfo> predecessors;
	ArrayList<RTJobInfo> successors;
	Timestamp endTime;


public RTJobInfo() {

}

	

}
