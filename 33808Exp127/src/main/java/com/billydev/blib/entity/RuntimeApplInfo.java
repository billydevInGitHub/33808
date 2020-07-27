package com.billydev.blib.entity;

import com.billydev.blib.model.Diagram_Line_Info;
import com.billydev.blib.model.Diagram_Rect_Info;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name = "RuntimeApplInfo")
public class RuntimeApplInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long rtapplId;


	public long getRtapplId() {
		return rtapplId;
	}

	public void setRtapplId(long rtapplId) {
		this.rtapplId = rtapplId;
	}

	/*
	 * the following is job info
	 * we can add this when needed
	 */
	@OneToMany(
			targetEntity=RTJobInfo.class,
			mappedBy = "rtappl_id",
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY
	)
	Set<RTJobInfo> jobs;




	String dtApplicationName;
	String state;

	String rtApplicationName;
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

	/*
	 * the following the diagram part
	 */
	ArrayList<Diagram_Rect_Info> datasetForRect = new ArrayList<>();
	ArrayList<Diagram_Line_Info> datasetForLine = new ArrayList<>();



	public String getDtApplicationName() {
		return dtApplicationName;
	}

	public void setDtApplicationName(String dtApplicationName) {
		this.dtApplicationName = dtApplicationName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Diagram_Rect_Info> getDatasetForRect() {
		return datasetForRect;
	}

	public void setDatasetForRect(ArrayList<Diagram_Rect_Info> datasetForRect) {
		this.datasetForRect = datasetForRect;
	}

	public ArrayList<Diagram_Line_Info> getDatasetForLine() {
		return datasetForLine;
	}

	public void setDatasetForLine(ArrayList<Diagram_Line_Info> datasetForLine) {
		this.datasetForLine = datasetForLine;
	}



	public Set<RTJobInfo> getJobs() {
		return jobs;
	}

	public void setJobs(Set<RTJobInfo> jobs) {
		this.jobs = jobs;
	}




	

}
