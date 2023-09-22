package com.hackathon.tsc.service;

import com.hackathon.tsc.constant.UserType;
import com.hackathon.tsc.dto.UserDTO;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.entity.CaseWorker;
import com.hackathon.tsc.entity.Navigator;
import com.hackathon.tsc.entity.User;
import com.hackathon.tsc.repository.BeneficiaryRepository;
import com.hackathon.tsc.repository.CaseWorkerRepository;
import com.hackathon.tsc.repository.NavigatorRepository;
import com.hackathon.tsc.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CaseWorkerRepository caseWorkerRepository;

    @Autowired
    private NavigatorRepository navigatorRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;



    public UserDTO validateCredentials(String userID, String password) throws UserNotFoundException {
        UserDTO userDTO = new UserDTO();
        Optional<User> userOptional = loginRepository.getUserByNameAndPassword(userID, password);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Invalid username and password");
        } else {
            User user = userOptional.get();
            userDTO.setUser(user);
            if (user.getUserType().equals(UserType.CASE_WORKER)) {
                Optional<CaseWorker> caseWorkerOptional = caseWorkerRepository.getCaseWorkerByUserID(user.getUserID());
                if (caseWorkerOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                CaseWorker caseWorker = caseWorkerOptional.get();
                userDTO.setCaseWorker(caseWorker);
            } else if (user.getUserType().equals(UserType.NAVIGATOR)) {
                Optional<Navigator> navigatorOptional = navigatorRepository.getNavigatorByUserID(user.getUserID());
                if (navigatorOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                Navigator navigator = navigatorOptional.get();
                userDTO.setNavigator(navigator);
            } else if (user.getUserType().equals(UserType.BENEFICIARY)) {
                Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.getBeneficiaryByUserID(user.getUserID());
                if (beneficiaryOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                Beneficiary beneficiary = beneficiaryOptional.get();
                userDTO.setBeneficiary(beneficiary);
            } else {
                throw new UserNotFoundException("User type not found");
            }
            return userDTO;
        }
    }

    public void getAttribute() {
        User user = new User("B1", "B1", "B1", "B1", UserType.BENEFICIARY.toString(), "B1");
        loginRepository.save(user);
        Beneficiary beneficiary = new Beneficiary("B1", "Cabin1", List.of("MH1"),
                List.of("SUD1"), List.of("N1"), "N1", List.of("D1"), List.of("SR1"));
        beneficiaryRepository.save(beneficiary);
    }
}