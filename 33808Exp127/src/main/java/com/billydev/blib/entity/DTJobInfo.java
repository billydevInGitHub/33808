package com.billydev.blib.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "DTJobInfo")
public class DTJobInfo {

	long jobId;
	String jobType;
	String DTJobName;
	String qualifier ;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "DTAppId", referencedColumnName = "DTAppId")
	})
	long DTAppId;

	public long getDTAppId() {
		return DTAppId;
	}

	public void setDTAppId(long DTAppId) {
		this.DTAppId = DTAppId;
	}

	String DTAppName;
	String predecessorCondition;
	String predecessorNames;
	String successorNames;
	String state;
	String scriptUserId;
	String agentName;
	String script;
    String argumentsOfScript;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getDTJobName() {
		return DTJobName;
	}

	public void setDTJobName(String DTJobName) {
		this.DTJobName = DTJobName;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getDTAppName() {
		return DTAppName;
	}

	public void setDTAppName(String DTAppName) {
		this.DTAppName = DTAppName;
	}

	public String getPredecessorCondition() {
		return predecessorCondition;
	}

	public void setPredecessorCondition(String predecessorCondition) {
		this.predecessorCondition = predecessorCondition;
	}

	public String getPredecessorNames() {
		return predecessorNames;
	}

	public void setPredecessorNames(String predecessorNames) {
		this.predecessorNames = predecessorNames;
	}

	public String getSuccessorNames() {
		return successorNames;
	}

	public void setSuccessorNames(String successorNames) {
		this.successorNames = successorNames;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getScriptUserId() {
		return scriptUserId;
	}

	public void setScriptUserId(String scriptUserId) {
		this.scriptUserId = scriptUserId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getArgumentsOfScript() {
		return argumentsOfScript;
	}

	public void setArgumentsOfScript(String argumentsOfScript) {
		this.argumentsOfScript = argumentsOfScript;
	}
}
