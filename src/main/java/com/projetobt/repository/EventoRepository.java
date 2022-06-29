package com.projetobt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetobt.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>
{
	Evento findByCodigo(long codigo);

}
