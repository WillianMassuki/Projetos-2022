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

import com.projetobt.models.Convidado;
import com.projetobt.models.Evento;
import com.projetobt.repository.ConvidadoRepository;
import com.projetobt.repository.EventoRepository;
import com.projetobt.service.ConvidadoDTO;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/convidados")
public class ConvidadoRest {
	
	private final ConvidadoRepository convidadoR;
	private final EventoRepository eventoRepository;

	public ConvidadoRest(ConvidadoRepository convidadoR, EventoRepository eventoRepository) {
		super();
		this.convidadoR = convidadoR;
		this.eventoRepository = eventoRepository;
	}
	
	@GetMapping
	public List<Convidado> listar()
	{
		return convidadoR.findAll();
	}
	
	///evento/{eventoCodigo}
	@PostMapping("/evento/{eventoCodigo}")
    public Convidado salvar(@PathVariable(value = "eventoCodigo") Long eventoCodigo,
        @Valid @RequestBody Convidado convidado){
        return eventoRepository.findById(eventoCodigo).map( evento ->
        {
        	convidado.setCodigo(convidado.getCodigo());
    		convidado.setNomeConvidado(convidado.getNomeConvidado());
    		convidado.setRg(convidado.getRg());
    		convidado.setEvento(evento);
    		return convidadoR.save(convidado);  
        	
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento inexistente"));
        		
    }

	
	@GetMapping("{codigo}")
	public Convidado listarPorId(@PathVariable Long codigo)
	{
	    return convidadoR
                .findById(codigo)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Convidado não encontrado") );
	}
	
	@DeleteMapping("{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long  codigo)
	{
		convidadoR.findById(codigo).map(convidado ->{
			convidadoR.delete(convidado);
			return Void.TYPE; }).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o convidado")); 
	}
	
	@PutMapping("{codigo}")
	public void atualizar(@PathVariable Long codigo, @RequestBody @Valid Convidado convidadoAtualizado )
	{
		convidadoR.findById(codigo).map(convidado -> {
			
			convidado.setNomeConvidado(convidadoAtualizado.getNomeConvidado());
			convidado.setRg(convidadoAtualizado.getRg());
			return convidadoR.save(convidado);
		}).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel atualizar as informaçoes do convidado")); 
		
	}
	
	/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Convidado salvar(@RequestBody @Valid Convidado convidado)
	{
		return convidadoR.save(convidado);
		
	}*/
	
/*
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Convidado salvar(@RequestBody ConvidadoDTO dto)
	{
		
		Long eventoCodigo = dto.getEventoCodigo();
		
		
		 Evento evento = eventoRepository.findById(eventoCodigo)
				 .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento inexistente"));
		
		Convidado convidado = new Convidado();
		convidado.setCodigo(dto.getCodigo());
		convidado.setNomeConvidado(dto.getNomeConvidado());
		convidado.setRg(dto.getRg());
		convidado.setEvento(evento);
		return convidadoR.save(convidado);  
		
	}*/
	

}
