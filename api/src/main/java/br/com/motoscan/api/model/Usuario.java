package br.com.motoscan.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements UserDetails { // Implemente a interface

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    // Métodos obrigatórios da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); } // Para apps simples, pode retornar uma lista vazia

    @Override
    public String getPassword() { return this.senha; }

    @Override
    public String getUsername() { return this.email; } // O "username" para o Spring é o nosso email

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}