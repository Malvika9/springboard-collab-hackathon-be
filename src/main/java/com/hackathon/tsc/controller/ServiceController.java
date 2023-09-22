package com.hackathon.tsc.controller;

import com.hackathon.tsc.entity.Service;
import com.hackathon.tsc.exception.ServiceNotFoundException;
import com.hackathon.tsc.exception.UserNotFoundException;
import com.hackathon.tsc.service.FileService;
import com.hackathon.tsc.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private Services services;

    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<List<Service>> getServices(@RequestParam String id, @RequestParam String beneficiaryID) {
        try {
            List<Service> servicesForBID = services.getServicesForID(id, beneficiaryID);
            return new ResponseEntity<>(servicesForBID, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/{beneficiaryID}")
//    public ResponseEntity<List<String>> getGoals(@PathVariable String beneficiaryID){
//        List<String> needs = services.getNeedForBID(beneficiaryID);
//        return new ResponseEntity<>(needs, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<List<Service>> addService(@RequestBody Service serviceToAdd) {
        try {
            List<Service> servicesForBID = services.addServiceForBID(serviceToAdd);
            return new ResponseEntity<>(servicesForBID, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/upload/{serviceID}")
    public ResponseEntity<Boolean> addFileToService(@PathVariable String serviceID, @RequestParam("file") MultipartFile file) {
        String fileName = serviceID + "_" + file.getOriginalFilename();
        fileService.saveFile(file, fileName);
        Service service = services.getServiceById(serviceID);
        service.getSupportingDocs().add(fileName);
        try {
            services.updateService(service);
        } catch (ServiceNotFoundException e) {
            return new ResponseEntity<>(true, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFileByFileName(@PathVariable String fileName) {
        return new ResponseEntity<>(fileService.downloadFile(fileName), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<List<Service>> deleteService(@RequestParam String navigatorID, @RequestParam String serviceID) {
        try {
            List<Service> servicesForBID = services.deleteServiceForBID(navigatorID, serviceID);
            return new ResponseEntity<>(servicesForBID, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<List<Service>> updateService(@RequestParam String id, @RequestBody Service updatedService) {
        try {
            List<Service> servicesForBID = services.updateService(id, updatedService);
            return new ResponseEntity<>(servicesForBID, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{beneficiaryID}")
    public ResponseEntity<List<String>> getGoals(@PathVariable String beneficiaryID){
        try {
            List<String> needs = services.getNeedForBID(beneficiaryID);
            return new ResponseEntity<>(needs, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}