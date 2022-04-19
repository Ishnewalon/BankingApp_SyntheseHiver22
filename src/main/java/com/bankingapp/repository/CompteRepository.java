package com.bankingapp.repository;

import com.bankingapp.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

    Compte getByUser_CourrielAndUser_Mdp(String email, String password);
}
