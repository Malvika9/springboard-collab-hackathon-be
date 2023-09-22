package com.hackathon.tsc.service;

import com.hackathon.tsc.Enum.UserType;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.pojo.Beneficiary;
import com.hackathon.tsc.pojo.CaseWorker;
import com.hackathon.tsc.pojo.Navigator;
import com.hackathon.tsc.pojo.User;
import com.hackathon.tsc.repository.BeneficiaryRepository;
import com.hackathon.tsc.repository.CaseWorkerRepository;
import com.hackathon.tsc.repository.NavigatorRepository;
import com.hackathon.tsc.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaseWorkerRepository caseWorkerRepository;

    @Autowired
    private NavigatorRepository navigatorRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private HttpSession httpSession;

    public User validateCredentials(String userName, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.getUserByNameAndPassword(userName, password);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("Invalid username and password");
        }
        else {
            User user = userOptional.get();
            if(user.getUserType().equals(UserType.CASE_WORKER.toString())) {
                Optional<CaseWorker> caseWorkerOptional = caseWorkerRepository.getCaseWorkerById(user.getId());
                if(caseWorkerOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                CaseWorker caseWorker = caseWorkerOptional.get();
                httpSession.setAttribute("user", user);
                httpSession.setAttribute("caseWorker", caseWorker);
            }
            else if(user.getUserType().equals(UserType.NAVIGATOR.toString())) {
                Optional<Navigator> navigatorOptional = navigatorRepository.getNavigatorById(user.getId());
                if(navigatorOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                Navigator navigator = navigatorOptional.get();
                httpSession.setAttribute("user", user);
                httpSession.setAttribute("navigator", navigator);
            }
            else if(user.getUserType().equals(UserType.BENEFICIARY.toString())) {
                Optional<Beneficiary> beneficiaryOptional = beneficiaryRepository.getBeneficiaryById(user.getId());
                if(beneficiaryOptional.isEmpty()) {
                    throw new UserNotFoundException("User not found");
                }
                Beneficiary beneficiary = beneficiaryOptional.get();
                httpSession.setAttribute("user", user);
                httpSession.setAttribute("beneficiary", beneficiary);
            }
            else {
                throw new UserNotFoundException("User id not found");
            }

            return user;
        }
    }

    public void logout() {
        httpSession.invalidate();
    }

    public void getAttribute() {
        System.out.println(httpSession.getAttributeNames());
    }

    public boolean isUserLoggedIn() {
        return httpSession.getAttribute("user") != null;
    }
}
