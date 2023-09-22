package com.hackathon.tsc.pojo;

import com.hackathon.tsc.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Notification {

    private Service service;
    private String source;
    private String sourceID;
    private String target;
    private String targetID;

}
