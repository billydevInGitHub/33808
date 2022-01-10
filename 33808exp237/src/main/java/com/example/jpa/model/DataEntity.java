package com.example.jpa.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "b33808exp237_DataEntity")
public class DataEntity {
    @Id
    private Long id;
    @Column(unique = true)
    private String testString;
    @Column
    private int testInt;

    @Column
    private LocalDateTime testLocalDateTime;

    public boolean isTestBoolean() {
        return testBoolean;
    }

    public void setTestBoolean(boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    public byte getTestByte() {
        return testByte;
    }

    public void setTestByte(byte testByte) {
        this.testByte = testByte;
    }

    public short getTestShort() {
        return testShort;
    }

    public void setTestShort(short testShort) {
        this.testShort = testShort;
    }

    public float getTestFloat() {
        return testFloat;
    }

    public void setTestFloat(float testFloat) {
        this.testFloat = testFloat;
    }

    public double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(double testDouble) {
        this.testDouble = testDouble;
    }

    public BigDecimal getTestBigDecimal() {
        return testBigDecimal;
    }

    public void setTestBigDecimal(BigDecimal testBigDecimal) {
        this.testBigDecimal = testBigDecimal;
    }

    public BigInteger getTestBigInteger() {
        return testBigInteger;
    }

    public void setTestBigInteger(BigInteger testBigInteger) {
        this.testBigInteger = testBigInteger;
    }

    public byte[] getTestByteArray() {
        return testByteArray;
    }

    public void setTestByteArray(byte[] testByteArray) {
        this.testByteArray = testByteArray;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Time getTestTime() {
        return testTime;
    }

    public void setTestTime(Time testTime) {
        this.testTime = testTime;
    }

    public Timestamp getTestTimeStamp() {
        return testTimeStamp;
    }

    public void setTestTimeStamp(Timestamp testTimeStamp) {
        this.testTimeStamp = testTimeStamp;
    }

    public String getTestClob() {
        return testClob;
    }

    public void setTestClob(String testClob) {
        this.testClob = testClob;
    }

    public byte[] getTestBlob() {
        return testBlob;
    }

    public void setTestBlob(byte[] testBlob) {
        this.testBlob = testBlob;
    }

    @Column
    private boolean testBoolean;
    @Column
    private byte testByte;
    @Column
    private short testShort;
    @Column
    private float testFloat;
    @Column
    private double testDouble;
    @Column
    private BigDecimal testBigDecimal;
    @Column
    private BigInteger testBigInteger;
    @Column
    private byte[] testByteArray;
    @Column
    private Date testDate;
    @Column
    private Time testTime;
    @Column
    private Timestamp testTimeStamp;
    @Lob
    private String testClob;
    @Lob
    private byte[] testBlob;
//    @Column
//    private String[] testStringArray;
//    @Column
//    private Integer[] testIntArray;

    public DataEntity() {

    }

    public DataEntity(Long id, String title) {
        this.id = id;
        this.testString = title;
    }

    public int getTestInt() {
        return testInt;
    }

    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String title) {
        this.testString = title;
    }
}
