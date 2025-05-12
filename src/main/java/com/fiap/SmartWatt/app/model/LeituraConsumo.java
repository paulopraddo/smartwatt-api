package com.fiap.SmartWatt.app.model;

import java.time.LocalDate;


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
public class LeituraConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLeitura;
    private LocalDate dataLeitura;
    private Double consumoTotalKw;

    @ManyToOne
    @JoinColumn(name = "id_residencia")
    private Residencia residencia;

    @ManyToOne
    @JoinColumn(name = "fonte_principal_id")
    private FonteEnergia fonteEnergiaPrincipal;
}
