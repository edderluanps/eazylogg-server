package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
