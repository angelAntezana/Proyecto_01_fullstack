package com.angel.backend_01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angel.backend_01.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    
    Optional<Usuario>findByEmail(String email);
}
