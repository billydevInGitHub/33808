package com.billydev.blib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billydev.blib.entity.EventInfo;

@Repository
public interface EventInfoRepository extends JpaRepository<EventInfo, Long> {

    
//    public List<EventInfo> findAll();

}
