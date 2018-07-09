package com.eduardotanaka.netflix.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardotanaka.netflix.api.dtos.UsuarioDto;
import com.eduardotanaka.netflix.api.entities.Usuario;
import com.eduardotanaka.netflix.api.enums.PerfilEnum;
import com.eduardotanaka.netflix.api.response.Response;
import com.eduardotanaka.netflix.api.services.UsuarioService;
import com.eduardotanaka.netflix.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {}
	
	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando usuário: {}", usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		validarDados(usuarioDto, result);
		Usuario usuario = this.converterDto(usuarioDto);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario u = this.usuarioService.salvar(usuario);
		
		usuarioDto.setId(u.getId());
		response.setData(usuarioDto);
		return ResponseEntity.ok(response);
	}
	
	private void validarDados(UsuarioDto usuario, BindingResult result) {
		this.usuarioService.buscarPorEmail(usuario.getEmail())
			.ifPresent(user -> result.addError(new ObjectError("usuario", "O email: " + usuario.getEmail() + " já está cadastrado")));
	}
	
	/**
	 * Converte os dados do DTO para Usuario
	 * 
	 * @param UsuarioDto
	 * @return Usuario
	 */
	private Usuario converterDto(UsuarioDto dto) throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setDataAtualizacao(Calendar.getInstance());
		usuario.setDataCriacao(Calendar.getInstance());
		usuario.setEmail(dto.getEmail());
		usuario.setPerfil(PerfilEnum.ROLE_USUARIO);
		usuario.setSenha(PasswordUtils.gerarBCrypt(dto.getSenha()));
		
		return usuario;
	}
}
