package com.hackathon.tsc.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Service {
    private String id;
    private String bId;
    private String cId;
    private String nId;
    private List<Activity> activityLog;
    private List<String> consent;
    private String status;
    private List<String> supportingDocs;   //string?

}
