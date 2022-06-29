package com.projetobt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobt.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	Optional<Usuario> findByUsername(String username);


}
