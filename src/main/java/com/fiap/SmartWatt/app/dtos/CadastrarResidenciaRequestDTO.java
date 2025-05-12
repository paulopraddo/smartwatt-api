package com.fiap.SmartWatt.app.dtos;

public record CadastrarResidenciaRequestDTO(String endereco, String tipoResidencia, Double areaM2, Integer numeroMoradores) {

}
