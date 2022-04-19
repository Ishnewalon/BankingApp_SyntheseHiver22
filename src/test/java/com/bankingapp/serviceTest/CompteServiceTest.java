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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

        Compte actualCompte = compteService.create(getDummyCompte());

        assertThat(actualCompte.getUser()).isEqualTo(dummyCompte.getUser());
    }

    @Test
    public void testCreate_invalidCompte() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.create(null));
    }

    @Test
    public void testFindAccount_validCompte() {
        Compte dummyCompte = getDummyCompte();
        User dummyUser = getDummyUser();
        when(compteRepository.getByUser_CourrielAndUser_Mdp(any(), any())).thenReturn(dummyCompte);

        Compte actualCompte = compteService.findAccount(dummyUser.getCourriel(), dummyUser.getMdp());

        assertThat(actualCompte.getUser()).isEqualTo(dummyCompte.getUser());
    }

    @Test
    public void testFindAccount_invalidCompte() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.findAccount(null, null));
    }

    @Test
    public void testDeposerMontant_valid() {
        Compte dummyCompte = getDummyCompte();
        Compte dummyCompteModified = getDummyCompte();
        dummyCompteModified.setSolde("200.00");
        when(compteRepository.save(any())).thenReturn(dummyCompteModified);

        Compte actualCompte = compteService.deposerMontant(100.0, dummyCompte);

        assertThat(actualCompte.getSolde()).isEqualTo(dummyCompteModified.getSolde());
    }

    @Test
    public void testDeposerMontant_invalidAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.deposerMontant(0, getDummyCompte()));
    }

    @Test
    public void testDeposerMontant_invalidCompte() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.deposerMontant(5, null));
    }

    @Test
    public void testRetirerMontant_valid() {
        Compte dummyCompte = getDummyCompte();
        Compte dummyCompteModified = getDummyCompte();
        dummyCompteModified.setSolde("50.00");
        when(compteRepository.save(any())).thenReturn(dummyCompteModified);

        Compte actualCompte = compteService.deposerMontant(50.0, dummyCompte);

        assertThat(actualCompte.getSolde()).isEqualTo(dummyCompteModified.getSolde());
    }

    @Test
    public void testRetirerMontant_invalidAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.deposerMontant(-1, getDummyCompte()));
    }

    @Test
    public void testRetirerMontant_invalidCompte() {
        assertThrows(IllegalArgumentException.class,
                () -> compteService.deposerMontant(5, null));
    }

    @Test
    public void testGetAllComptes() {
        List<Compte> dummyCompteListe = getDummyCompteList();
        when(compteRepository.getAll()).thenReturn(dummyCompteListe);

        List<Compte> actualCompteListe = compteService.getAllComptes();

        assertThat(actualCompteListe.size()).isEqualTo(3);
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

    private List<Compte> getDummyCompteList() {
        List<Compte> dummyCompteList = new ArrayList<>();

        Compte dummyCompte1 = new Compte();
        dummyCompte1.setId(1);
        dummyCompte1.setSolde("100.00");
        dummyCompte1.setUser(getDummyUser());

        Compte dummyCompte2 = new Compte();
        dummyCompte2.setId(2);
        dummyCompte2.setSolde("200.00");
        dummyCompte2.setUser(getDummyUser());

        Compte dummyCompte3 = new Compte();
        dummyCompte3.setId(3);
        dummyCompte3.setSolde("300.00");
        dummyCompte3.setUser(getDummyUser());

        dummyCompteList.add(dummyCompte1);
        dummyCompteList.add(dummyCompte2);
        dummyCompteList.add(dummyCompte3);

        return dummyCompteList;
    }
}
