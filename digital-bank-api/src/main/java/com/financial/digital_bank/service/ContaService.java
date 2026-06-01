package com.financial.digital_bank.service;

import com.financial.digital_bank.domain.Enum.TipoMovimentacao;
import com.financial.digital_bank.domain.event.TransferenciaNotificacaoEvent;
import com.financial.digital_bank.dto.ContaCreateRequest;
import com.financial.digital_bank.dto.ContaResponse;
import com.financial.digital_bank.dto.TransferenciaRequest;
import com.financial.digital_bank.domain.entity.Conta;
import com.financial.digital_bank.domain.entity.Movimentacao;
import com.financial.digital_bank.exception.BusinessException;
import com.financial.digital_bank.infrastructure.kafka.NotificacaoProducer;
import com.financial.digital_bank.repository.ContaRepository;
import com.financial.digital_bank.repository.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ApplicationEventPublisher publisher;
    private final NotificacaoProducer producer;

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public ContaResponse criarConta(ContaCreateRequest request) {

        Conta conta = new Conta();

        conta.setTitular(request.getTitular());
        conta.setSaldo(request.getSaldo());

        Conta salvar = contaRepository.save(conta);

        return new ContaResponse(salvar.getId(),
                salvar.getTitular(),
                salvar.getSaldo());
    }

    @Transactional
    public void transferir(TransferenciaRequest request) {

        if (request.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Valor da transferência deve ser maior que zero");
        }

        Conta origem = contaRepository.findById(request.getContaOrigemId())
                .orElseThrow(() -> new BusinessException("Conta origem não encontrada"));

        Conta destino = contaRepository.findById(request.getContaDestinoId())
                .orElseThrow(() -> new BusinessException("Conta destino não encontrada"));

        if (origem.getSaldo().compareTo(request.getValor()) < 0) {
            throw new BusinessException("Saldo insuficiente");
        }

        //Conta de origem
        origem.setSaldo(origem.getSaldo().subtract(request.getValor()));

        //Conta de destino
        destino.setSaldo(destino.getSaldo().add(request.getValor()));

        //Movimentação de saída
        Movimentacao saida = new Movimentacao();
        saida.setConta(origem);
        saida.setValor(request.getValor());
        saida.setTipo(TipoMovimentacao.TRANSFERENCIA_SAIDA);

        //Movimentação de entrada
        Movimentacao entrada = new Movimentacao();
        entrada.setConta(destino);
        entrada.setValor(request.getValor());
        entrada.setTipo(TipoMovimentacao.TRANSFERENCIA_ENTRADA);

        movimentacaoRepository.save(saida);
        movimentacaoRepository.save(entrada);
        contaRepository.save(origem);
        contaRepository.save(destino);

        //Envia notificacao
        producer.publish(
                new TransferenciaNotificacaoEvent(
                        origem.getId(),
                        destino.getId(),
                        request.getValor()
                )
        );
    }
}
