package com.eduardotanaka.netflix.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.eduardotanaka.netflix.api.entities.Usuario;
import com.eduardotanaka.netflix.api.enums.PerfilEnum;
import com.eduardotanaka.netflix.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired UsuarioRepository usurioRepository;
	private final String EMAIL = "email@teste.com";
	
	// roda antes do test
	@Before
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail("email@teste.com");
		String senha = PasswordUtils.gerarBCrypt("123456");
		usuario.setSenha(senha);
		usuario.setDataAtualizacao(Calendar.getInstance());
		usuario.setDataCriacao(Calendar.getInstance());
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		
		this.usurioRepository.save(usuario);
	}
	
	// roda depois do test
	@After
	public void tearDown() {
		this.usurioRepository.deleteAll();
	}
	
	// testa se o email retorna algum usuário e o email são iguais
	@Test
	public void testBuscarPorEmail() {
		Usuario usuario = this.usurioRepository.findByEmail(EMAIL);
		
		assertEquals(usuario.getEmail(), EMAIL);
	}
}
