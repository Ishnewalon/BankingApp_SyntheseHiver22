package com.bankingapp.entity;

import lombok.Data;

@Data
public class NewAccountDTO {

    private String nom;

    private String prenom;

    private String courriel;

    private String mdp;

    private String telephone;

    private String adresse;

    private int age;

    private String occupation;

    String adminEmail;

    String adminPassword;
}
