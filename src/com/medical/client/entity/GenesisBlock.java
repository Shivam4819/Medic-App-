package com.medical.client.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


@JsonPropertyOrder(value = {
        "id","creationDate","creationTime","companyName","doctorName","previousBlockHash",
        "currentBlockHash"
})
public class GenesisBlock implements Serializable{
    private  long id;
    private  Date creationDate;
    private  Time creationTime;
    private  String companyName;
    private  String previousBlockHash;
    private  String currentBlockHash;

    public  long getId() {
        return id;
    }

    public  void setId(long id) {
        this.id = id;
    }

    public  Date getCreationDate() {
        return creationDate;
    }

    public  void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public  Time getCreationTime() {
        return creationTime;
    }

    public  void setCreationTime(Time creationTime) {
        this.creationTime = creationTime;
    }

    public  String getCompanyName() {
        return companyName;
    }

    public  void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public  String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public  void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public  String getCurrentBlockHash() {
        return currentBlockHash;
    }

    public  void setCurrentBlockHash(String currentBlockHash) {
        this.currentBlockHash = currentBlockHash;
    }
}

