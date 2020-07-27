package com.billydev.blib.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.billydev.blib.model.Diagram_Line_Info;
import com.billydev.blib.model.Diagram_Rect_Info;
import com.billydev.blib.entity.RTJobInfo;
import com.billydev.blib.entity.RuntimeApplInfo;

@Repository
public interface RuntimeApplRepository extends JpaRepository<RuntimeApplInfo,Long> {


    public RuntimeApplInfo findByrtapplId(Long id);
//    public RuntimeApplInfo get_Runtime_Appl_info(long  appl_id)
//    public long  insert_Runtime_Appl_info(RuntimeApplInfo rt_appl_info) {
//	public RuntimeApplInfo get_Runtime_Appl_info_By_RT_Job_Id(long job_id)


}
