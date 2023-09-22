package com.hackathon.tsc.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Beneficiary extends User {

    private Long id;
    private String cabinNo;
    private List<String> mhProvider;
    private List<String> sudProvider;
    private List<String> need;
    private Long navigatorId;   //assigned navigator
    private List<String> documents; //should it be the actual document or just the name of it?
    private List<Long> serviceRequestId;

    public Beneficiary(Long id, String firstName, String lastName, String userId, String password, String userType, String contactNo, Long id1, String cabinNo, List<String> mhProvider, List<String> sudProvider, List<String> need, Long navigatorId, List<String> documents, List<Long> serviceRequestId) {
        super(id, firstName, lastName, userId, password, userType, contactNo);
        this.id = id1;
        this.cabinNo = cabinNo;
        this.mhProvider = mhProvider;
        this.sudProvider = sudProvider;
        this.need = need;
        this.navigatorId = navigatorId;
        this.documents = documents;
        this.serviceRequestId = serviceRequestId;
    }

    public Beneficiary(Long id, String cabinNo, List<String> mhProvider, List<String> sudProvider, List<String> need, Long navigatorId, List<String> documents, List<Long> serviceRequestId) {
        this.id = id;
        this.cabinNo = cabinNo;
        this.mhProvider = mhProvider;
        this.sudProvider = sudProvider;
        this.need = need;
        this.navigatorId = navigatorId;
        this.documents = documents;
        this.serviceRequestId = serviceRequestId;
    }
}
