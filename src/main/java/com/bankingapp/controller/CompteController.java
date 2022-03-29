package com.bankingapp.controller;

import com.bankingapp.entity.Compte;
import com.bankingapp.entity.Credentials;
import com.bankingapp.entity.NewAccountDTO;
import com.bankingapp.entity.User;
import com.bankingapp.service.CompteService;
import com.bankingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String ouvertureCompte(@ModelAttribute("nouveauClient") NewAccountDTO newAccountDTO, Model model) {
        User nouveauClient = new User();
        nouveauClient.setNom(newAccountDTO.getNom());
        nouveauClient.setPrenom(newAccountDTO.getPrenom());
        nouveauClient.setCourriel(newAccountDTO.getCourriel());
        nouveauClient.setMdp(newAccountDTO.getMdp());
        nouveauClient.setTelephone(newAccountDTO.getTelephone());
        nouveauClient.setAdresse(newAccountDTO.getAdresse());
        nouveauClient.setAge(newAccountDTO.getAge());
        nouveauClient.setOccupation(newAccountDTO.getOccupation());
        nouveauClient = userService.create(nouveauClient);

        Compte nouveauCompte = new Compte();
        nouveauCompte.setUser(nouveauClient);
        nouveauCompte.setSolde("0.00");
        compteService.create(nouveauCompte);
        Compte compte = compteService.findAccount(newAccountDTO.getAdminEmail(), newAccountDTO.getAdminPassword());
        model.addAttribute("account", compte);

        return "dashboard";
    }

    @GetMapping("/loginPage")
    public String goToLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="credentials") Credentials credentials, Model model) {
        Compte compte = compteService.findAccount(credentials.getCourriel(), credentials.getMdp());
        model.addAttribute("account", compte);
        return "dashboard";
    }

    @GetMapping("/signup/{courriel}/{mdp}")
    public String goToSignUp(Model model, @PathVariable String courriel, @PathVariable String mdp) {
       NewAccountDTO newAccountDTO = new NewAccountDTO();
        model.addAttribute("nouveauClient",newAccountDTO);
        model.addAttribute("adminEmail", courriel);
        model.addAttribute("adminPassword", mdp);
        return "signup";
    }

    @GetMapping("/dashboard")
    public String goToDashboard() {
        return "dashboard";
    }
}
