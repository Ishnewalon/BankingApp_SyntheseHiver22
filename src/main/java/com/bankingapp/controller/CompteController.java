package com.bankingapp.controller;

import com.bankingapp.entity.Compte;
import com.bankingapp.entity.Credentials;
import com.bankingapp.entity.User;
import com.bankingapp.service.CompteService;
import com.bankingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompteController {

    private final CompteService compteService;

    private final UserService userService;

    CompteController(CompteService compteService, UserService userService) {
        this.compteService = compteService;
        this.userService = userService;
    }

    @PostMapping("/ouverture/compte")
    public String ouvertureCompte(@ModelAttribute("NouveauClient") User user) {
        User nouveauClient = userService.create(user);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setUser(nouveauClient);
        nouveauCompte.setSolde("0.00");
        compteService.create(nouveauCompte);

        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="credentials") Credentials credentials, Model model) {
        Compte compte = compteService.findAccount(credentials.getCourriel(), credentials.getMdp());
        model.addAttribute("account", compte
                );
        return "dashboard";
    }
}
