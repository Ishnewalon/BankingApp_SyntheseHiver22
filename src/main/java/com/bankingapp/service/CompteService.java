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

    public Compte create(User user) {
        Assert.isTrue(user != null, "Le client doit exister");
        Compte nouveauCompte = new Compte();
        nouveauCompte.setUser(user);
        nouveauCompte.setSolde("0.00");

        return compteRepository.save(nouveauCompte);
    }
}
