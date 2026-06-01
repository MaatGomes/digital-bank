package com.financial.digital_bank.infrastructure.kafka;

import com.financial.digital_bank.domain.event.TransferenciaNotificacaoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificacaoConsumer {
    @KafkaListener(
            topics = "transfer-completed",
            groupId = "notification-group"
    )
    public void consume(
            TransferenciaNotificacaoEvent event
    ) {

        log.info(
                "Notificação enviada para transferência de R$ {}",
                event.valor()
        );
    }
}
