package com.example.api.com.front.Repository;

import com.example.api.com.front.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.nome = ?1")
    List<Usuario> findAllByNome(String nome);
}
