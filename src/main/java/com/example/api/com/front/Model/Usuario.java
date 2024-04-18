package com.example.api.com.front.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "nome é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Email
    @NotBlank(message = "email é obrigatório")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "senha é obrigatório")
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank(message = "telefone é obrigatório")
    @Column(name = "telefone", nullable = false)
    private String telefone;
}
