package com.financial.digital_bank.controller;

import com.financial.digital_bank.domain.entity.Conta;
import com.financial.digital_bank.domain.entity.Movimentacao;
import com.financial.digital_bank.dto.ContaCreateRequest;
import com.financial.digital_bank.dto.ContaResponse;
import com.financial.digital_bank.dto.TransferenciaRequest;
import com.financial.digital_bank.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    @PostMapping("/criar")
    public ResponseEntity<ContaResponse> criarConta(@Valid @RequestBody ContaCreateRequest request) {

        ContaResponse contaCriada = contaService.criarConta(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @PostMapping("/transferencia")
    public ResponseEntity<Void> transferir(@RequestBody TransferenciaRequest request) {

        contaService.transferir(request);

        return ResponseEntity.ok().build();
    }
}
