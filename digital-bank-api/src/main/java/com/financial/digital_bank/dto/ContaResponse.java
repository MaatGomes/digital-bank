package com.financial.digital_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ContaResponse {

    private UUID id;
    private String titular;
    private BigDecimal saldo;
}
