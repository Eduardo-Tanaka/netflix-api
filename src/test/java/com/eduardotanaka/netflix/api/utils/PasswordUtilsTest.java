package com.eduardotanaka.netflix.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PasswordUtilsTest {

	private final String SENHA = "123456";
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void textSenhaNull() {
		assertNull(PasswordUtils.gerarBCrypt(null));
	}
	
	@Test
	public void testEncryptSenha() {
		String senhaBCrypt = PasswordUtils.gerarBCrypt(SENHA);
		
		assertTrue(encoder.matches(SENHA, senhaBCrypt));
	}
}
