package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    //@Transactional(readOnly = true)
    //@Query("SELECT a FROM Avaliacao a WHERE a.usuarioId = id")
    //public List<Avaliacao> findAvaliacaoPorId(@Param("id") Long usuarioId);
}
