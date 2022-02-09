package com.bankingapp.service;

import com.bankingapp.entity.Compte;
import com.bankingapp.entity.User;
import com.bankingapp.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public Compte create(Compte compte) {
        Assert.isTrue(compte != null, "Le compte doit exister");

        return compteRepository.save(compte);
    }
}
