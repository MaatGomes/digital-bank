package com.financial.digital_bank.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferenciaNotificacaoEvent(
        UUID contaOrigemId,
        UUID contaDestinoId,
        BigDecimal valor
) {
}

