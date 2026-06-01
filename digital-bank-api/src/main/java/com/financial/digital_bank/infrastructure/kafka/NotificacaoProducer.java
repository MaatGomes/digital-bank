package com.financial.digital_bank.infrastructure.kafka;

import com.financial.digital_bank.domain.event.TransferenciaNotificacaoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class NotificacaoProducer {

    private final KafkaTemplate<String, TransferenciaNotificacaoEvent> kafkaTemplate;

    public void publish(TransferenciaNotificacaoEvent event) {

        kafkaTemplate.send(
                "transfer-completed",
                event
        );
    }
}
