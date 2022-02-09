package com.bankingapp.serviceTest;

import com.bankingapp.entity.User;
import com.bankingapp.repository.UserRepository;
import com.bankingapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreate_validUser() {
        User dummyUser = getDummyUser();
        when(userRepository.save(any())).thenReturn(dummyUser);

        User actualUser = userService.create(getDummyUser());

        assertThat(actualUser.getCourriel()).isEqualTo(dummyUser.getCourriel());
    }

    @Test
    public void testCreate_invalidUser() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.create(null));
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
