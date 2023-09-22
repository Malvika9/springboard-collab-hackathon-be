package com.hackathon.tsc.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaseWorker extends User {

    private Long id;
    private String organisationName;
    private String organisationType;
    private List<Long> serviceId;

    public CaseWorker(Long id, String firstName, String lastName, String userId, String password, String userType, String contactNo, String organisationName, String organisationType, List<Long> serviceId) {
        super(id, firstName, lastName, userId, password, userType, contactNo);
        this.id = id;
        this.organisationName = organisationName;
        this.organisationType = organisationType;
        this.serviceId = serviceId;
    }

    public CaseWorker(Long id, String organisationName, String organisationType, List<Long> serviceId) {
        this.id = id;
        this.organisationName = organisationName;
        this.organisationType = organisationType;
        this.serviceId = serviceId;
    }
}
