package com.example.jpa.repository;

import com.example.jpa.model.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntityRepository extends JpaRepository<DataEntity,Long> {
}
