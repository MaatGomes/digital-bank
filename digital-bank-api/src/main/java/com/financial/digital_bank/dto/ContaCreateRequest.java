package com.financial.digital_bank.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContaCreateRequest {

    private String titular;
    @PositiveOrZero(message = "O saldo inicial não pode ser negativo")
    private BigDecimal saldo;
}
