package com.eduardotanaka.netflix.api.services;

import java.util.Optional;

import com.eduardotanaka.netflix.api.entities.Usuario;

public interface UsuarioService {

	/**
	 * Persiste um usuário no banco de dados
	 * @param Usuario: usuario
	 * @return Usuario
	 */
	Usuario salvar(Usuario usuario);
	
	/**
	 * Busca e retorna um usuário pelo email
	 * @param String: email
	 * @return Usuario
	 */
	Optional<Usuario> buscarPorEmail(String email);
	
	/**
	 * Busca e retorna um usuário pelo id
	 * @param Long: id
	 * @return USuario
	 */
	Optional<Usuario> buscarPorId(Long id);
}
