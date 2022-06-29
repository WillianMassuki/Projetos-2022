package com.projetobt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projetobt.models.Evento;
import com.projetobt.repository.EventoRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/eventos")
public class EventoRest {
	
	private final EventoRepository repository;

	public EventoRest(EventoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Evento> listar()
	{
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento salvar(@RequestBody @Valid Evento evento )
	{
		return repository.save(evento);
		
	}
	
	@GetMapping("{id}")
	public Evento listarPorId(@PathVariable Long id)
	{
	    return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado") );
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long  id)
	{
		repository.findById(id).map(evento ->{
			repository.delete(evento);
			return Void.TYPE; }).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o evento")); 
	}
	
	@PutMapping("{codigo}")
	public void atualizar(@PathVariable Long codigo, @RequestBody @Valid Evento eventoAtualizado )
	{
		repository.findById(codigo).map(evento -> {
			
			evento.setNome(eventoAtualizado.getNome());
			evento.setLocal(eventoAtualizado.getLocal());
			evento.setHorario(eventoAtualizado.getHorario());
			evento.setData(eventoAtualizado.getData());
			return repository.save(evento);
		}).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o evento")); 
		
	}
	
}
