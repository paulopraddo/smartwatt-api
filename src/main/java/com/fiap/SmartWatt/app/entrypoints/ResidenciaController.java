package com.fiap.SmartWatt.app.entrypoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.SmartWatt.app.dtos.AlterarNumeroDeMoradoresRequestDTO;
import com.fiap.SmartWatt.app.dtos.CadastrarResidenciaRequestDTO;
import com.fiap.SmartWatt.app.model.Residencia;
import com.fiap.SmartWatt.app.service.ResidenciaService;

@RestController
@RequestMapping("/api/residencias")
public class ResidenciaController {

    @Autowired
    private ResidenciaService residenciaService;

    @PostMapping
    public ResponseEntity<Residencia> criarResidencia(@RequestBody CadastrarResidenciaRequestDTO dto) {
        Residencia residencia = this.residenciaService.cadastrarResidencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(residencia);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Residencia> encontrarResidencia(@PathVariable Long id) {
        Residencia residencia = this.residenciaService.encontrarResidenciaPeloId(id);
        return ResponseEntity.ok().body(residencia);
    }

    @PutMapping("/id={id}")
    public ResponseEntity<String> alterarNumeroDeMordadres(@PathVariable Long id, @RequestBody AlterarNumeroDeMoradoresRequestDTO dto) {
        this.residenciaService.alterarNumeroDeMoradores(id, dto.valor());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deletarResidencia(@PathVariable Long id) {
        this.residenciaService.deletarResidenciaPeloId(id);
        return ResponseEntity.ok().build();
    }
}