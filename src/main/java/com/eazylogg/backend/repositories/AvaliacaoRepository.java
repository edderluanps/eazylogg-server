package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.usuario.id = :idUsuario")
    Double findAvgUser(@Param("idUsuario") Long id);
}
