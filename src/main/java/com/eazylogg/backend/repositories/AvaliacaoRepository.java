package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
