package com.eduardotanaka.netflix.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardotanaka.netflix.api.entities.Usuario;
import com.eduardotanaka.netflix.api.repositories.UsuarioRepository;
import com.eduardotanaka.netflix.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario salvar(Usuario usuario) {
		log.info("Salvando usuário: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando usuario pelo email: {}", email);
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		log.info("Buscando pelo id: {}", id);
		return Optional.ofNullable(this.usuarioRepository.findById(id).get());
	}

}
