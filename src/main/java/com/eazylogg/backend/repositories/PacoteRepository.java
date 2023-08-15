package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Pacote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {

    @Transactional(readOnly=true)
    Page<Pacote> findAll(Pageable pageRequest);

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Pacote p WHERE p.porte =:porte AND p.cepDestinatario =:cep")
    List<Pacote> pesquisa (@Param("porte") String porte, @Param("cep") String cep);
}
