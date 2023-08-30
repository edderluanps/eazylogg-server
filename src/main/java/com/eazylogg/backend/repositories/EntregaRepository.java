package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT e FROM Entrega e WHERE e.entregadorId.id =:entId")
    List<Entrega> findByEntregadorId(@Param("entId") Long entId);

}
