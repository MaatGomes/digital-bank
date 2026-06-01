package com.financial.digital_bank.infrastructure.listener;

import com.financial.digital_bank.domain.event.TransferenciaRealizadaEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {

    @Async
    @EventListener
    public void onTransferencia(TransferenciaRealizadaEvent event) {

        System.out.println("Notificação enviada:");
        System.out.println("Transferência de R$ " + event.getValor()
                + " realizada com sucesso!");
    }
}
