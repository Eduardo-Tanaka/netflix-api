package com.eduardotanaka.netflix.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.eduardotanaka.netflix.api.entities.Usuario;
import com.eduardotanaka.netflix.api.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Before
	public void setUp() {
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(new Usuario());
	}

	@Test
	public void testSalvarUsuario() {
		Usuario usuario = this.usuarioService.salvar(new Usuario());
		
		assertNotNull(usuario);
	}
	
	@Test
	public void testBuscarPorEmail() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail("email");
		
		assertTrue(usuario.isPresent());
	}
}
