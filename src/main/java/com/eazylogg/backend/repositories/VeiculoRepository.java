package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Transactional(readOnly=true)
    @Query("SELECT v FROM Veiculo v WHERE v.usuario.id =:id")
    List<Veiculo> getVeiculoByUserId(Long id);

}
