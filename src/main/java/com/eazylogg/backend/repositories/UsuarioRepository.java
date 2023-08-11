package com.eazylogg.backend.repositories;

import com.eazylogg.backend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Transactional(readOnly = true)
    Usuario findByEmail(String email);

    @Transactional(readOnly = true)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(concat('%', :pesquisa,'%')) AND u.categoria =:categoria")
    List<Usuario> pesquisa (@Param("pesquisa") String pesquisa, @Param("categoria") String categoria);

}
