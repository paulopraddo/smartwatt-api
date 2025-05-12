package com.fiap.SmartWatt.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.SmartWatt.app.model.Residencia;

@Repository
public interface ResidenciaRepository extends JpaRepository <Residencia, Long> {

}
