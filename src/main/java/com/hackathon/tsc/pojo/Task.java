package com.hackathon.tsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String taskID;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;
    private String beneficiaryID;
    private String navigatorID;

}
