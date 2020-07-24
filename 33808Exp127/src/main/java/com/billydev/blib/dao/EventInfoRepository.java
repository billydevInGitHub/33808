package com.billydev.blib.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.billydev.blib.model.DT_Appl_Info;
import com.billydev.blib.model.DT_Job_Info;
import com.billydev.blib.model.Event_Info;

@Repository
public interface EventInfoRepository extends JpaRepository<Event_Info, Long> {

    
    public List<Event_Info> findAll();

}
