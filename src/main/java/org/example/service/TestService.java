package org.example.service;

import org.example.domain.alpha.AlphaEntity;
import org.example.domain.beta.BetaEntity;
import org.example.repository.alpha.AlphaRepo;
import org.example.repository.beta.BetaRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TestService {
    private final AlphaRepo alphaRepo;
    private final BetaRepo betaRepo;

    public TestService(AlphaRepo alphaRepo, BetaRepo betaRepo) {
        this.alphaRepo = alphaRepo;
        this.betaRepo = betaRepo;
    }


    @Transactional
    public String test() {
//        try {
            AlphaEntity alpha = new AlphaEntity("data", 1);
            alphaRepo.save(alpha);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
            BetaEntity beta = new BetaEntity("data", 1);
            betaRepo.save(beta);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Success");
        return "Success";
    }
}
