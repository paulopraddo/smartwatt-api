package com.fiap.SmartWatt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.SmartWatt.app.model.MetaConsumo;

@Repository
public interface MetaConsumoRepository extends JpaRepository<MetaConsumo, Long> {

    MetaConsumo findByResidenciaIdAndMesReferencia(Long residenciaId, String mesReferencia);

}
