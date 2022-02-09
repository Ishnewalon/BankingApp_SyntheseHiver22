package com.bankingapp.controller;

import com.bankingapp.entity.Compte;
import com.bankingapp.entity.User;
import com.bankingapp.service.CompteService;
import com.bankingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompteController {

    private final CompteService compteService;

    private final UserService userService;

    CompteController(CompteService compteService, UserService userService) {
        this.compteService = compteService;
        this.userService = userService;
    }

/*    @PostMapping("/ouverture/compte")
    public Compte ouvertureCompte(@ModelAttribute("NouveauClient") User user) {

    }*/
}
