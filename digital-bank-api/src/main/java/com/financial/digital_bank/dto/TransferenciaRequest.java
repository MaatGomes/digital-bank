package com.financial.digital_bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TransferenciaRequest {

    private UUID contaOrigemId;
    private UUID contaDestinoId;
    private BigDecimal valor;
}
