package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "name")
    private String nombre;
    @Getter @Setter @Column(name = "lastname")
    private String apellido;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telephone")
    private String telefono;
    @Getter @Setter @Column(name = "password")
    private String password;
}