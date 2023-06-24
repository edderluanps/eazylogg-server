package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
