package br.com.motoscan.api.model;

import jakarta.persistence.*;
import lombok.Data; // Lombok para gerar getters, setters, toString, etc.

@Entity // Diz ao JPA que esta classe é uma tabela
@Data   // Anotação do Lombok
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    @Column(unique = true) 
    private String placa;

    private String zona;

  
    private boolean falhaMecanica;
    private boolean multa;
    private boolean roubada;

   
}