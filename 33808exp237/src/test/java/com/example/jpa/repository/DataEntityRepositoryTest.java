package com.example.jpa.repository;

import com.example.jpa.model.DataEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaSystemException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataEntityRepositoryTest {
    @Autowired
    DataEntityRepository dataEntityRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @AfterEach
    void tearDown() {
//        dataEntityRepository.deleteAll();
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
        assertEquals(dataEntityFromDB.getTestString(), dataEntity.getTestString());
    }

    @Test
    void testInteger(){
        DataEntity dataEntity = new DataEntity(1l, "test integer");
        dataEntity.setTestInt(Integer.MAX_VALUE);
        dataEntityRepository.save(dataEntity);
        DataEntity dataEntityFromDB = dataEntityRepository.findById(1l).orElseThrow(()->new RuntimeException("empty"));
        assertEquals(dataEntityFromDB.getTestInt(), dataEntity.getTestInt());
    }

    @Test
    void testString(){
        DataEntity dataEntity = new DataEntity(1l, "test String");
        StringBuilder  stringBuilder=new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("0123456789");
        }
        dataEntity.setTestString(stringBuilder.toString());
        dataEntityRepository.save(dataEntity);
        DataEntity dataEntityFromDB = dataEntityRepository.findById(1l).orElseThrow(()->new RuntimeException("empty"));
        assertEquals(dataEntityFromDB.getTestString().length(), dataEntity.getTestString().length());
    }

    @Test
    void testClob(){
        DataEntity dataEntity = new DataEntity(1l, "test String");
        StringBuilder  stringBuilder=new StringBuilder();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append("0123456789");
        }
        dataEntity.setTestClob(stringBuilder.toString());
        dataEntityRepository.save(dataEntity);
        DataEntity dataEntityFromDB = dataEntityRepository.findById(1l).orElseThrow(()->new RuntimeException("empty"));
        assertEquals(dataEntityFromDB.getTestClob().length(), dataEntity.getTestClob().length());
    }

    @Test
    void testStringOverFlow(){
        DataEntity dataEntity = new DataEntity(1l, "test String");
        StringBuilder  stringBuilder=new StringBuilder();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append("0123456789");
        }
        dataEntity.setTestString(stringBuilder.toString());
        assertThrows(JpaSystemException.class, () -> dataEntityRepository.save(dataEntity));
//        DataEntity dataEntityFromDB = dataEntityRepository.findById(1l).orElseThrow(()->new RuntimeException("empty"));
//        assertEquals(dataEntityFromDB.getTestString().length(), dataEntity.getTestString().length());
    }

//    @Test
//    @Disabled
//    void testIntegerRange(){
//        DataEntity dataEntity = new DataEntity(1l, "test integer");
//        dataEntity.setTestInt(Integer.MAX_VALUE-1);
//        dataEntityRepository.save(dataEntity);
//        jdbcTemplate.execute("update B33808EXP237_DATA_ENTITY set TEST_INT=TEST_INT+2");
//        DataEntity dataEntityFromDB=null;
//        assertThrows(GenericJDBCException.class,()->{dataEntityRepository.findById(1l);});
////        assertEquals(dataEntityFromDB.getTestInt(), Integer.MAX_VALUE+2);
//    }
}