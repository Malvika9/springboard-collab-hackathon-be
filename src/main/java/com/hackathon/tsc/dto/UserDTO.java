package com.hackathon.tsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.entity.CaseWorker;
import com.hackathon.tsc.entity.Navigator;
import com.hackathon.tsc.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private User user;
    private Beneficiary beneficiary;
    private CaseWorker caseWorker;
    private Navigator navigator;
}
