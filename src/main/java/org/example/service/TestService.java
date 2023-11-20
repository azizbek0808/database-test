package org.example.service;

import org.example.domain.alpha.AlphaEntity;
import org.example.domain.beta.BetaEntity;
import org.example.repository.alpha.AlphaRepo;
import org.example.repository.beta.BetaRepo;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final AlphaRepo alphaRepo;
    private final BetaRepo betaRepo;

    public TestService(AlphaRepo alphaRepo, BetaRepo betaRepo) {
        this.alphaRepo = alphaRepo;
        this.betaRepo = betaRepo;
    }


    public void test() {
        AlphaEntity alpha = new AlphaEntity("data", 1);
        BetaEntity beta = new BetaEntity("data", 1);
        alphaRepo.save(alpha);
        betaRepo.save(beta);
        System.out.println("Success");
    }
}
