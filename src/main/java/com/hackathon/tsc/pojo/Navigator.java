package com.hackathon.tsc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Navigator extends User {

    private Long id;
    List<Long> assignedBeneficiariesId;

    public Navigator(Long id, String firstName, String lastName, String userId, String password, String userType, String contactNo, List<Long> assignedBeneficiariesId) {
        super(id, firstName, lastName, userId, password, userType, contactNo);
        this.id = id;
        this.assignedBeneficiariesId = assignedBeneficiariesId;
    }

    public Navigator(Long id, List<Long> assignedBeneficiariesId) {
        this.id = id;
        this.assignedBeneficiariesId = assignedBeneficiariesId;
    }

    public Navigator() {

    }
}
