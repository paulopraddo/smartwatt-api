package com.fiap.SmartWatt.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.SmartWatt.app.dtos.CadastrarResidenciaRequestDTO;
import com.fiap.SmartWatt.app.exceptions.ResidenciaNotFoundException;
import com.fiap.SmartWatt.app.model.Residencia;
import com.fiap.SmartWatt.app.repository.ResidenciaRepository;

@Service
public class ResidenciaService {

    @Autowired
    private ResidenciaRepository residenciaRepository;

    public Residencia cadastrarResidencia(CadastrarResidenciaRequestDTO dto) {

        Residencia residencia = new Residencia();
        residencia.setEndereco(dto.endereco());
        residencia.setTipoResidencia(dto.tipoResidencia());
        residencia.setAreaM2(dto.areaM2());
        residencia.setNumeroMoradores(dto.numeroMoradores());

        return residenciaRepository.save(residencia);
    }

    public Residencia encontrarResidenciaPeloId(Long id) {

        Residencia residencia = this.residenciaRepository.findById(id)
                .orElseThrow(() -> new ResidenciaNotFoundException("Residencia não encontrada"));
        
        return residencia;
    }

    public void alterarNumeroDeMoradores(Long id, Integer valor) {
        Residencia residencia = encontrarResidenciaPeloId(id);

        residencia.setNumeroMoradores(valor);

        residenciaRepository.save(residencia);
    }

    public void deletarResidenciaPeloId(Long id) {
        residenciaRepository.deleteById(id);
    }
}
