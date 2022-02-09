package com.bankingapp.controllerTest;

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

public class UserControllerTest {

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserSignUp() throws Exception {
        User dummyUser = getDummyUser();
        when(userService.create(any())).thenReturn(dummyUser);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/inscrire/client")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(MAPPER.writeValueAsString(dummyUser)))
                    .andReturn();

        final MockHttpServletResponse response = mvcResult.getResponse();
        User actualUser = MAPPER.readValue(response.getContentAsString(), User.class);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(actualUser.getNom()).isEqualTo(dummyUser);
    }
}
