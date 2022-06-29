package com.projetobt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetobt.models.Convidado;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
//	List<Convidado> findByEventoCodigo(Long eventoCodigo);

}
