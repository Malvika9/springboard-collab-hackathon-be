package com.hackathon.tsc.Enum;

public enum UserType {
    NAVIGATOR("Navigator"),
    CASE_WORKER("CaseWorker"),
    BENEFICIARY("Beneficiary");

    private String type;

     UserType(String type) {
        this.type = type;
    }

}
