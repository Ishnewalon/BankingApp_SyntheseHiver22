package com.bankingapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Compte {

    @Id
    @GeneratedValue
    int id;

    private String solde;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    User user;
}
