package com.hackathon.tsc.controller;

import com.hackathon.tsc.entity.Beneficiary;
import com.hackathon.tsc.service.LoginService;
import com.hackathon.tsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tsc/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @Autowired
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<Beneficiary>> getAssignedBeneficiaries(@RequestParam String id) {
        List<Beneficiary> beneficiaryList = userService.getAssignedBeneficiaries(id);
        return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
    }

    @GetMapping("/beneficiaries/unassigned")
    public ResponseEntity<List<Beneficiary>> getUnassignedBeneficiaries() {
        List<Beneficiary> beneficiaryList = userService.getUnassignedBeneficiaries();
        return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
    }

    @PutMapping("/beneficiaries/assignNavigator")
    public ResponseEntity<String> assignNavigator(@RequestParam String beneficiaryID, @RequestParam String navigatorID) {
        boolean result = userService.assignNavigator(beneficiaryID, navigatorID);
        return ResponseEntity.ok("Navigator assigned to beneficiary");
    }
    @PutMapping("/beneficiaries/unAssignNavigator")
    public ResponseEntity<String> unAssignNavigator(@RequestParam String beneficiaryID) {
        boolean result = userService.unAssignNavigator(beneficiaryID);
        return ResponseEntity.ok("Navigator unassigned for beneficiary");
    }

    @PostMapping("/addBeneficiary")
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary newBeneficiary) {
        Beneficiary updatedBeneficiaryList = userService.addBeneficiary(newBeneficiary);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBeneficiaryList);
    }

    @DeleteMapping("/deleteBeneficiary")
    public ResponseEntity<Boolean> deleteBeneficiary(@RequestParam String beneficiaryID) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.deleteBeneficiary(beneficiaryID));
    }

}
