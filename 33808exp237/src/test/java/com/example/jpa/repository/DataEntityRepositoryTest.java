package com.example.jpa.repository;

import com.example.jpa.model.DataEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataEntityRepositoryTest {
    @Autowired
    DataEntityRepository dataEntityRepository;

    @AfterEach
    void tearDown() {
        dataEntityRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        dataEntityRepository.deleteAll();
    }

    @Test
    void quickTest() {
        DataEntity dataEntity = new DataEntity(1l, "first entry");
        dataEntityRepository.save(dataEntity);
        DataEntity dataEntityFromDB = dataEntityRepository.findById(1l).orElseThrow(()->new RuntimeException("empty"));
        assertEquals(dataEntityFromDB.getTitle(), dataEntity.getTitle());
    }
}