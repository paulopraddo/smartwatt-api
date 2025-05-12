package com.fiap.SmartWatt.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.SmartWatt.app.dtos.CadastrarResidenciaRequestDTO;
import com.fiap.SmartWatt.app.exceptions.ResidenciaNotFoundException;
import com.fiap.SmartWatt.app.model.Residencia;
import com.fiap.SmartWatt.app.repository.ResidenciaRepository;

public class ResidenciaServiceTest {

    @InjectMocks
    private ResidenciaService residenciaService;

    @Mock
    private ResidenciaRepository residenciaRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarResidenciaComSucesso() {
        CadastrarResidenciaRequestDTO dto = new CadastrarResidenciaRequestDTO("testEndereco", "testTipo", 2.0, 2);

        residenciaService.cadastrarResidencia(dto);

        verify(residenciaRepository, times(1)).save(any());
    }
    
    @Test
    void deveEncontrarResidencia() {
        Residencia residencia = new Residencia();

        when(residenciaRepository.findById(anyLong())).thenReturn(Optional.of(residencia));

        Residencia resoponse = residenciaService.encontrarResidenciaPeloId(1L);

        assertEquals(residencia, resoponse);
        verify(residenciaRepository, times(1)).findById(anyLong());
    }

     @Test
    void deveAlterarNumeroDeMoradoresComSucesso() {
        Long id = 1L;
        Integer novoNumero = 5;
        Residencia residencia = new Residencia();
        residencia.setId(id);
        residencia.setNumeroMoradores(3);

        when(residenciaRepository.findById(id)).thenReturn(Optional.of(residencia));

        residenciaService.alterarNumeroDeMoradores(id, novoNumero);

        assertEquals(novoNumero, residencia.getNumeroMoradores());
        verify(residenciaRepository).save(residencia);
    }

    @Test
    void deveLancarExcecaoQuandoResidenciaNaoForEncontrada() {
        Long id = 999L;
        when(residenciaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResidenciaNotFoundException.class, () ->
            residenciaService.encontrarResidenciaPeloId(id)
        );
    }

    @Test
    void deveDeletarResidenciaPorIdComSucesso() {
        Long id = 1L;

        residenciaService.deletarResidenciaPeloId(id);

        verify(residenciaRepository).deleteById(id);
    }
}
