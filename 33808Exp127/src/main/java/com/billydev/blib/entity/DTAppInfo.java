package com.billydev.blib.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Table(name = "DTAppInfo")
public class DTAppInfo {


	public String getdTAppName() {
		return dTAppName;
	}

	public void setdTAppName(String dTAppName) {
		this.dTAppName = dTAppName;
	}

	String dTAppName;
	String appScript;
	Timestamp uploadTime ;

	@OneToMany(
			targetEntity=DTJobInfo.class,
			mappedBy = "DTAppId",
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY
	)
	Set<DTJobInfo> DTJobs;


	public Set<DTJobInfo> getDTJobs() {
		return DTJobs;
	}

	public void setDTJobs(Set<DTJobInfo> DTJobs) {
		this.DTJobs = DTJobs;
	}



	public String getAppScript() {
		return appScript;
	}

	public void setAppScript(String appScript) {
		this.appScript = appScript;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getAppCreateUserId() {
		return appCreateUserId;
	}

	public void setAppCreateUserId(String appCreateUserId) {
		this.appCreateUserId = appCreateUserId;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	String appCreateUserId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long appId;


	
}
