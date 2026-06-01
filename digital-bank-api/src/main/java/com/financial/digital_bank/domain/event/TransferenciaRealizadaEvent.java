package com.financial.digital_bank.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TransferenciaRealizadaEvent {

    private final UUID contaOrigemId;
    private final UUID contaDestinoId;
    private final BigDecimal valor;
}
