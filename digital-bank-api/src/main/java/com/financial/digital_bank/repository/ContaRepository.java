package com.financial.digital_bank.repository;

import com.financial.digital_bank.domain.entity.Conta;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
}
