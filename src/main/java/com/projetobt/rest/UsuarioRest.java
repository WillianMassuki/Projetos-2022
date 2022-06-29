package com.projetobt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetobt.models.Usuario;
import com.projetobt.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/usuario")
public class UsuarioRest {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioRest(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	

	@GetMapping
	public List<Usuario> listar()
	{
		return usuarioRepository.findAll();
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario)
	{
		 usuarioRepository.save(usuario);
		
	}
	/*
	@DeleteMapping("{login}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String login)
	{
		usuarioRepository.findById(login).map(usuario ->{
			usuarioRepository.delete(usuario);
			return Void.TYPE; }).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel deletar o convidado")); 
	}*/
	/*
	@PutMapping("{login}")
	public void atualizar(@PathVariable String login, @RequestBody @Valid Usuario usuarioAtualizado )
	{
		usuarioRepository.findById(login).map(usuario -> {
			
			usuario.setLogin(usuarioAtualizado.getLogin());
			usuario.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
			usuario.setSenha(usuarioAtualizado.getSenha());
			return usuarioRepository.save(usuario);
		}).orElseThrow( ()->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel atualizar as informaçoes do convidado")); 
		
	}*/
	

}
