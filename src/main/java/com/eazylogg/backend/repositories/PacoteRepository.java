package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
}
