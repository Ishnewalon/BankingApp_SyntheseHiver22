package com.bankingapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Compte {

    @Id
    @GeneratedValue
    int id;
}
