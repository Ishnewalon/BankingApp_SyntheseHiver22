package com.bankingapp.serviceTest;


import com.bankingapp.entity.Compte;
import com.bankingapp.entity.User;
import com.bankingapp.repository.CompteRepository;
import com.bankingapp.service.CompteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompteServiceTest {

    @InjectMocks
    private CompteService compteService;

    @Mock
    private CompteRepository compteRepository;

    @Test
    public void testCreate_validCompte() {
        Compte dummyCompte = getDummyCompte();
        when(compteRepository.save(any())).thenReturn(dummyCompte);

        Compte actualCompte = compteService.create(getDummyUser());

        assertThat(actualCompte.getUser()).isEqualTo(dummyCompte.getUser());
    }

    private Compte getDummyCompte() {
        Compte dummyCompte = new Compte();
        dummyCompte.setId(1);
        dummyCompte.setSolde("100.00");
        dummyCompte.setUser(getDummyUser());

        return dummyCompte;
    }

    private User getDummyUser() {
        User dummyUser = new User();
        dummyUser.setId(35);
        dummyUser.setPrenom("Cindi");
        dummyUser.setNom("Desjardins");
        dummyUser.setOccupation("Consultante");
        dummyUser.setAge(45);
        dummyUser.setAdresse("1234 rue Inexistant, Montreal, QC");
        dummyUser.setCourriel("c.desj@gmail.com");
        dummyUser.setMdp("consult");
        dummyUser.setTelephone("514-654-2346");

        return dummyUser;
    }
}
