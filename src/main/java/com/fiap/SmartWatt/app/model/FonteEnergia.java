package com.fiap.SmartWatt.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class FonteEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFonte;
    private String tipoFonte;
    private Double capacidadeKw;

    @ManyToOne
    @JoinColumn(name = "id_residencia")
    private Residencia residencia;
}
