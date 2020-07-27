package com.billydev.blib.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.billydev.blib.entity.DTAppInfo;
import com.billydev.blib.entity.DTJobInfo;

@Repository
public interface DesigntimeApplRepository extends JpaRepository<DTAppInfo,Long> {

//	public DTAppInfo getDesignTime_Appl_info(String dt_Application_Name) {
//public Boolean deleteDesignTimeAppl(long appl_id) {
//public DTAppInfo updateDesignTimeAppl(DTAppInfo dtAppInfo) {
//public Boolean createDesignTimeAppl(DTAppInfo dtAppInfo) {
//public ArrayList<DTAppInfo> listAllDesignTimeAppls(){

	DTAppInfo findByDTAppName(String dtAppName);
}
