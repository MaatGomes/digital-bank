package com.financial.digital_bank.service;

import com.financial.digital_bank.domain.entity.Conta;
import com.financial.digital_bank.dto.ContaCreateRequest;
import com.financial.digital_bank.dto.ContaResponse;
import com.financial.digital_bank.dto.TransferenciaRequest;
import com.financial.digital_bank.exception.BusinessException;
import com.financial.digital_bank.infrastructure.kafka.NotificacaoProducer;
import com.financial.digital_bank.repository.ContaRepository;
import com.financial.digital_bank.repository.MovimentacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private NotificacaoProducer producer;

    @Test
    void deveTransferirComSucesso() {

        UUID origemId = UUID.randomUUID();
        UUID destinoId = UUID.randomUUID();

        Conta origem = new Conta();
        origem.setId(origemId);
        origem.setSaldo(new BigDecimal("500"));

        Conta destino = new Conta();
        destino.setId(destinoId);
        destino.setSaldo(new BigDecimal("100"));

        when(contaRepository.findById(origemId)).thenReturn(Optional.of(origem));
        when(contaRepository.findById(destinoId)).thenReturn(Optional.of(destino));

        TransferenciaRequest request = new TransferenciaRequest();
        request.setContaOrigemId(origemId);
        request.setContaDestinoId(destinoId);
        request.setValor(new BigDecimal("200"));

        contaService.transferir(request);

        assertEquals(new BigDecimal("300"), origem.getSaldo());
        assertEquals(new BigDecimal("300"), destino.getSaldo());

        verify(producer).publish(any());
    }

    @Test
    void deveLancarErroQuandoSaldoInsuficiente() {

        UUID origemId = UUID.randomUUID();
        UUID destinoId = UUID.randomUUID();

        Conta origem = new Conta();
        origem.setId(origemId);
        origem.setSaldo(new BigDecimal("50"));

        Conta destino = new Conta();
        destino.setId(destinoId);
        destino.setSaldo(new BigDecimal("100"));

        when(contaRepository.findById(origemId)).thenReturn(Optional.of(origem));
        when(contaRepository.findById(destinoId)).thenReturn(Optional.of(destino));

        TransferenciaRequest request = new TransferenciaRequest();
        request.setContaOrigemId(origemId);
        request.setContaDestinoId(destinoId);
        request.setValor(new BigDecimal("200"));

        assertThrows(BusinessException.class, () -> {
            contaService.transferir(request);
        });
    }

    @Test
    void deveCriarContaComSaldoZeroQuandoNaoInformado() {

        ContaCreateRequest request = new ContaCreateRequest();
        request.setTitular("Mateus");
        request.setSaldo(null);

        Conta contaMock = new Conta();
        contaMock.setId(UUID.randomUUID());
        contaMock.setTitular("Mateus");
        contaMock.setSaldo(BigDecimal.ZERO);

        when(contaRepository.save(any(Conta.class)))
                .thenReturn(contaMock);

        ContaResponse result = contaService.criarConta(request);

        assertEquals(BigDecimal.ZERO, result.getSaldo());
        assertEquals("Mateus", result.getTitular());
    }
}
