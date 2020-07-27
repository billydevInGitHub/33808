package com.billydev.blib.dao;

import com.billydev.blib.entity.RTJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuntimeJobRepository  extends JpaRepository<RTJobInfo,Long> {

}
