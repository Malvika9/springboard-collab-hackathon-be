package com.hackathon.tsc.service;

import com.hackathon.tsc.constant.UserType;
import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.exception.ServiceNotFoundException;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.repository.BeneficiaryRepository;
import com.hackathon.tsc.repository.LoginRepository;
import com.hackathon.tsc.repository.ServiceRepository;
import com.hackathon.tsc.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hackathon.tsc.constant.ExceptionMessages.*;

@org.springframework.stereotype.Service
public class Services {

    @Autowired
    private ServiceRepository servicesRepository;
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private LoginService loginService;

    public List<Service> getServicesForID(String userID, String beneficiaryID) throws UserNotFoundException {
        Optional<String> userType = loginRepository.getUserTypeByUserID(userID);
        if (userType.isPresent())
            return servicesRepository.getServicesForId(userType.get(), userID, beneficiaryID);
        else
            throw  new UserNotFoundException(USER_NOT_FOUND);
    }

    public List<Service> addServiceForBID(Service service) throws UserNotFoundException {
        servicesRepository.addService(service);
        return servicesRepository.getServicesForId(UserType.NAVIGATOR, service.getNavigatorID(), service.getBeneficiaryID());
    }

    public List<Service> deleteServiceForBID(String navigatorID, String serviceID) throws ServiceNotFoundException, UserNotFoundException {
        Optional<String> userType = loginRepository.getUserTypeByUserID(navigatorID);
        if (userType.isPresent() && userType.get().equals(UserType.NAVIGATOR)) {
            Service service = servicesRepository.getServiceById(serviceID).get();
            servicesRepository.deleteService(serviceID);
            return servicesRepository.getServicesForId(userType.get(), navigatorID, service.getBeneficiaryID());
        }
        throw new UserNotFoundException(USER_NOT_LOGGED_IN);
    }

    public List<Service> updateService(String id, Service updatedService) throws ServiceNotFoundException, UserNotFoundException {
        servicesRepository.updateService(updatedService);
        Optional<String> user = loginRepository.getUserTypeByUserID(id);
        if (user.isPresent() && user.get().equals(UserType.NAVIGATOR)) {
            return servicesRepository.getServicesForId(UserType.NAVIGATOR, updatedService.getNavigatorID(), updatedService.getBeneficiaryID());
        } else if (user.equals(UserType.CASE_WORKER)) {
            return servicesRepository.getServicesForId(UserType.CASE_WORKER, updatedService.getCaseWorkerID(), updatedService.getBeneficiaryID());
        }
        return new ArrayList<>();
    }

    public void updateService(Service service) throws ServiceNotFoundException {
        servicesRepository.updateService(service);
    }

    public Service getServiceById(String id) {
        return servicesRepository.getServiceById(id).get();
    }

    public List<String> getNeedForBID(String beneficiaryID) throws UserNotFoundException {
        Optional<Beneficiary> beneficiary = beneficiaryRepository.getBeneficiaryByUserID(beneficiaryID);
        if(beneficiary.isPresent())
        {
            return beneficiary.get().getNeed();
        }
        else
            throw new UserNotFoundException(USER_NOT_FOUND);
    }
}
