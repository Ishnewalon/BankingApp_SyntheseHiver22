package com.bankingapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    int id;

    private String nom;

    private String prenom;

    private String courriel;

    private String mdp;

    private String telephone;

    private String adresse;

    private String age;

    private String occupation;

}
