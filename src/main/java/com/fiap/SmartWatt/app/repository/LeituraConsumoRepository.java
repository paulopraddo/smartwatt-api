package com.fiap.SmartWatt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.SmartWatt.app.model.LeituraConsumo;

@Repository
public interface LeituraConsumoRepository extends JpaRepository<LeituraConsumo, Long>{

    LeituraConsumo findTopByResidenciaIdOrderByDataLeituraDesc(Long residenciaId);
}
