package com.billydev.blib.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billydev.blib.entity.RTAppInfo;

@Repository
public interface RuntimeApplRepository extends JpaRepository<RTAppInfo,Long> {


//    public RTAppInfo findByrtapplId(Long id);
//    public RuntimeApplInfo get_Runtime_Appl_info(long  appl_id)
//    public long  insert_Runtime_Appl_info(RuntimeApplInfo rt_appl_info) {
//	public RuntimeApplInfo get_Runtime_Appl_info_By_RT_Job_Id(long job_id)


}
