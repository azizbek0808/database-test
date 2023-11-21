package org.example.service;

import org.example.domain.alpha.Alpha2Entity;
import org.example.domain.alpha.AlphaEntity;
import org.example.domain.beta.BetaEntity;
import org.example.repository.alpha.Alpha2Repo;
import org.example.repository.alpha.AlphaRepo;
import org.example.repository.beta.BetaRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestService {
    private final AlphaRepo alphaRepo;
    private final Alpha2Repo alpha2Repo;
    private final BetaRepo betaRepo;

    public TestService(AlphaRepo alphaRepo, Alpha2Repo alpha2Repo, BetaRepo betaRepo) {
        this.alphaRepo = alphaRepo;
        this.alpha2Repo = alpha2Repo;
        this.betaRepo = betaRepo;
    }


    @Transactional(value = "chainedTransactionManager")
    public String test() {
        try {
            AlphaEntity alpha = new AlphaEntity("data", 1);
            alphaRepo.save(alpha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            BetaEntity beta = new BetaEntity("data", 1);
            betaRepo.save(beta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Success");
        return "Success";
    }


    @Transactional
    public String test2() {

        try {
            AlphaEntity alpha = new AlphaEntity("data", 1);
            alphaRepo.save(alpha);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Alpha2Entity alpha2 = new Alpha2Entity("data", 1);
            alpha2Repo.save(alpha2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Success");
        return "Success";
    }
}
