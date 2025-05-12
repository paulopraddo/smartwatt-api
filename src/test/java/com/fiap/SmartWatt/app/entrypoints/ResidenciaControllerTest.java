package com.fiap.SmartWatt.app.entrypoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fiap.SmartWatt.app.dtos.AlterarNumeroDeMoradoresRequestDTO;
import com.fiap.SmartWatt.app.dtos.CadastrarResidenciaRequestDTO;
import com.fiap.SmartWatt.app.model.Residencia;
import com.fiap.SmartWatt.app.service.ResidenciaService;

public class ResidenciaControllerTest {

    @InjectMocks
    private ResidenciaController residenciaController;

    @Mock
    private ResidenciaService residenciaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarResidenciaComSucesso() {
        CadastrarResidenciaRequestDTO dto = new CadastrarResidenciaRequestDTO("testEndereco", "testTipo", 2.0, 2);
        
        Residencia residencia = new Residencia();

        when(residenciaService.cadastrarResidencia(any())).thenReturn(residencia);

        ResponseEntity<Residencia> response = residenciaController.criarResidencia(dto);

        assertEquals(residencia, response.getBody());
    }

    @Test
    void deveRetornarUmaResidenciaComSucesso() {
        Residencia residencia = new Residencia();

        when(residenciaService.encontrarResidenciaPeloId(any())).thenReturn(residencia);

        ResponseEntity<Residencia> response = residenciaController.encontrarResidencia(2L);

        assertEquals(residencia, response.getBody());
    }

    @Test
    void deveRetornarSucessoQuandoAlterar() {
        AlterarNumeroDeMoradoresRequestDTO dto = new AlterarNumeroDeMoradoresRequestDTO(2);

        ResponseEntity<String> response = residenciaController.alterarNumeroDeMordadres(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(residenciaService, times(1)).alterarNumeroDeMoradores(any(), any());
    }

    @Test
    void deveRetornarSucessoQuandoDeletar() {
        ResponseEntity<String> response = residenciaController.deletarResidencia(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(residenciaService, times(1)).deletarResidenciaPeloId(any());
    }
}
