package com.bankingapp.service;

import com.bankingapp.entity.User;
import com.bankingapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        Assert.isTrue(user != null, "Le client doit exister");

        return userRepository.save(user);
    }
}
