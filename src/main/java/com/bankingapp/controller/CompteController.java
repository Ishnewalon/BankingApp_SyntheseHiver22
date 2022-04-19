package com.bankingapp.controller;

import com.bankingapp.entity.*;
import com.bankingapp.service.CompteService;
import com.bankingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/dashboard/{currentCompte}")
    public String goToDashboard(@PathVariable Compte currentCompte, Model model) {
        model.addAttribute("account", currentCompte);
        return "dashboard";
    }

    @GetMapping("/toDeposit/{currentCompte}")
    public String goToDeposit(@PathVariable Compte currentCompte, Model model) {
        model.addAttribute("account", currentCompte);
        model.addAttribute("depositAmount", new AmountDTO());
        return "deposit";
    }

    @PostMapping("/makeDeposit/{currentCompte}")
    public String makeDeposit(@ModelAttribute(name="depositAmount") AmountDTO amountDTO, @PathVariable Compte currentCompte, Model model) {
        currentCompte = compteService.deposerMontant(amountDTO.getMoneyAmount(), currentCompte);
        model.addAttribute("account", currentCompte);
        return "dashboard";
    }

    @GetMapping("/toWithdrawl/{currentCompte}")
    public String goToWithdrawl(@PathVariable Compte currentCompte, Model model) {
        model.addAttribute("account", currentCompte);
        model.addAttribute("withdrawlAmount", new AmountDTO());
        return "withdrawl";
    }

    @PostMapping("/makeWithdrawl/{currentCompte}")
    public String makeWithdrawl(@ModelAttribute(name="withdrawlAmount") AmountDTO amountDTO, @PathVariable Compte currentCompte, Model model) {
        currentCompte = compteService.reitrerMontant(amountDTO.getMoneyAmount(), currentCompte);
        model.addAttribute("account", currentCompte);
        return "dashboard";
    }

    @GetMapping("/toList/{currentCompte}")
    public String goToList(@PathVariable Compte currentCompte, Model model) {
        List<Compte> compteList = compteService.getAllComptes();
        model.addAttribute("account", currentCompte);
        model.addAttribute("accountList", compteList);
        return "clientList";
    }
}
