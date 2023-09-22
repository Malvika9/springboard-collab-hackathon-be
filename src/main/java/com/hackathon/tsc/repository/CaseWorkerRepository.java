package com.hackathon.tsc.repository;

import com.hackathon.tsc.Enum.OrganizationType;
import com.hackathon.tsc.pojo.CaseWorker;
import com.hackathon.tsc.pojo.Navigator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseWorkerRepository {

    List<CaseWorker> caseWorkers;

    public CaseWorkerRepository() {
        caseWorkers = List.of(new CaseWorker(5L, "Org1", OrganizationType.MENTAL_HEALTH_PROVIDER.toString(), List.of(1L)));
    }

    public Optional<CaseWorker> getCaseWorkerById(Long id) {
        return caseWorkers.stream().filter(navigator ->
                navigator.getId().equals(id)
        ).findFirst();
    }
}
