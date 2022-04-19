package com.bankingapp.controllerTest;

import com.bankingapp.controller.CompteController;
import com.bankingapp.entity.*;
import com.bankingapp.service.CompteService;
import com.bankingapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CompteController.class)
public class CompteControllerTest {

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CompteService compteService;

    @MockBean
    UserService userService;

    @Test
    public void testUserSignUp() throws Exception {
        NewAccountDTO dummyNewAccountDTO = getDummyAccountDTO();
        Compte dummyCompte = getDummyCompte();
        when(userService.create(any())).thenReturn(getDummyUser());
        when(compteService.create(any())).thenReturn(dummyCompte);
        when(compteService.findAccount(any(), any())).thenReturn(dummyCompte);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/ouverture/compte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(dummyNewAccountDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    public void testUserLogin() throws Exception {
        Credentials dummyCredentials = new Credentials();
        dummyCredentials.setCourriel("c.desj@gmail.com");
        dummyCredentials.setMdp("consult");
        Compte dummyCompte = getDummyCompte();
        when(compteService.findAccount(any(), any())).thenReturn(dummyCompte);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(dummyCredentials)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    public void testCompteCreation() throws Exception {
        Compte dummyCompte = getDummyCompte();
        User dummyUser = getDummyUser();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/signup/" + dummyUser.getCourriel() + "/" + dummyUser.getMdp())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(dummyCompte)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("signup"));
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

    private NewAccountDTO getDummyAccountDTO() {
        NewAccountDTO dummyNewAccountDTO = new NewAccountDTO();
        dummyNewAccountDTO.setPrenom("Cindi");
        dummyNewAccountDTO.setNom("Desjardins");
        dummyNewAccountDTO.setOccupation("Consultante");
        dummyNewAccountDTO.setAge(45);
        dummyNewAccountDTO.setAdresse("1234 rue Inexistant, Montreal, QC");
        dummyNewAccountDTO.setCourriel("c.desj@gmail.com");
        dummyNewAccountDTO.setMdp("consult");
        dummyNewAccountDTO.setTelephone("514-654-2346");
        dummyNewAccountDTO.setAdminEmail("a@admin.com");
        dummyNewAccountDTO.setAdminPassword("password");

        return dummyNewAccountDTO;
    }
}
