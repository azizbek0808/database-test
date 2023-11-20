package org.example.service;

import org.example.domain.alfa.AlfaEntity;
import org.example.domain.beta.BetaEntity;
import org.example.repository.alfa.AlfaRepo;
import org.example.repository.beta.BetaRepo;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final AlfaRepo alfaRepo;
    private final BetaRepo betaRepo;

    public TestService(AlfaRepo alfaRepo, BetaRepo betaRepo) {
        this.alfaRepo = alfaRepo;
        this.betaRepo = betaRepo;
    }


    public void test() {
        AlfaEntity alpha = new AlfaEntity();
        alpha.setData("data");
        alpha.setCount(1);

        BetaEntity beta = new BetaEntity();
        beta.setData("data");
        beta.setCount(1);

        alfaRepo.save(alpha);
        betaRepo.save(beta);
        System.out.println("Success");
    }
}
