package com.hackathon.tsc.repository;

import com.hackathon.tsc.pojo.Beneficiary;
import com.hackathon.tsc.pojo.Navigator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryRepository {

    List<Beneficiary> dummyBeneficiary;

    public BeneficiaryRepository() {
        dummyBeneficiary = List.of(new Beneficiary(1L, "MH19", List.of("MH1"), List.of("S1"), List.of("N1"),
               3L, List.of("D1"), List.of(1L)));
    }

    public Optional<Beneficiary> getBeneficiaryById(Long id) {
        return dummyBeneficiary.stream().filter(navigator ->
                navigator.getId().equals(id)
        ).findFirst();
    }
}
