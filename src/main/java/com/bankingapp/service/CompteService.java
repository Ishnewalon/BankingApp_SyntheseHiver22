package com.bankingapp.service;

import com.bankingapp.entity.Compte;
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

    public Compte findAccount(String email, String password) {
        Assert.isTrue(email != null && password != null, "Le courriel ou le mot de passe" +
                "ne sont pas valide");
        return compteRepository.getByUser_CourrielAndUser_Mdp(email, password);
    }

    public Compte deposerMontant(double montantADeposer, Compte compte) {
        Assert.isTrue(montantADeposer != 0, "Le montant doit être plus que 0$");
        Assert.isTrue(compte != null, "Le compte doit exister");
        double nouveauSolde = Double.parseDouble(compte.getSolde()) + montantADeposer;
        compte.setSolde(Double.toString(nouveauSolde));
        return create(compte);
    }
}
