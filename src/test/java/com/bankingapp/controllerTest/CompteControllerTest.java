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
}
