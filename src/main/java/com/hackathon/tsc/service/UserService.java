package com.hackathon.tsc.service;



import com.hackathon.tsc.constant.UserType;
import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.entity.CaseWorker;
import com.hackathon.tsc.entity.Navigator;
import com.hackathon.tsc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private NavigatorRepository navigatorRepository;
    @Autowired
    private CaseWorkerRepository caseWorkerRepository;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Beneficiary> getAssignedBeneficiaries(String userID) {
        //query and return assigned beneficiaries
        List<Beneficiary> beneficiariesList = new ArrayList<>();
        Optional<String> userType = loginRepository.getUserTypeByUserID(userID);
        if(userType.isPresent() && userType.get().equals(UserType.NAVIGATOR)) {
            Optional<Navigator> navigator = navigatorRepository.getNavigatorByUserID(userID);
            if(navigator.isPresent()) {
                List<String> beneficiariesIds = navigator.get().getAssignedBeneficiariesId();
                beneficiariesList = beneficiaryRepository.getBeneficiariesByIdList(beneficiariesIds);
            }
        } else if (userType.isPresent() && userType.get().equals(UserType.CASE_WORKER)) {
            Optional<CaseWorker> caseWorker = caseWorkerRepository.getCaseWorkerByUserID(userID);
            if(caseWorker.isPresent()) {
                List<String> beneficiariesIds = new ArrayList<>(caseWorker.get().getServiceID().
                        stream().map(serviceId -> serviceRepository.getServiceById(serviceId).get().
                                getBeneficiaryID()).collect(Collectors.toSet()));
                beneficiariesList = beneficiaryRepository.getBeneficiariesByIdList(beneficiariesIds);
            }
        }
        return beneficiariesList;
    }

    public List<Beneficiary> getUnassignedBeneficiaries() {
        //query and return unassigned beneficiaries
        List<Beneficiary> assignedBeneficiaries = new ArrayList<>();
        assignedBeneficiaries.add(example);
        return assignedBeneficiaries;
    }

    public boolean assignNavigator(String beneficiaryID, String navigatorID) {
        //update nId field for beneficiary and return
        return beneficiaryRepository.assignNavigator(beneficiaryID, navigatorID);
    }

    public boolean unAssignNavigator(String beneficiaryID) {
        //update nId field to null for beneficiary and return
        return beneficiaryRepository.assignNavigator(beneficiaryID, null);
    }

    public Beneficiary addBeneficiary(Beneficiary newBeneficiary) {
        beneficiaryRepository.save(newBeneficiary);
        return newBeneficiary;
    }

    public boolean deleteBeneficiary(String beneficiaryID) {
        return beneficiaryRepository.deleteBeneficiary(beneficiaryID);
    }
}
