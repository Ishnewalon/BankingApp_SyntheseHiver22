package com.bankingapp.controllerTest;

import com.bankingapp.entity.Compte;
import com.bankingapp.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CompteControllerTest {

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserSignUp() throws Exception {
        Compte dummyCompte = getDummyCompte();
        when(compteService.create(any())).thenReturn(dummyCompte);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/ouverture/compte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(MAPPER.writeValueAsString(dummyCompte)))
                .andReturn();

        final MockHttpServletResponse response = mvcResult.getResponse();
        Compte actualCompte = MAPPER.readValue(response.getContentAsString(), Compte.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualCompte).isEqualTo(dummyCompte);
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
