package com.eduardotanaka.netflix.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UsuarioDto {
	
	private Long id;

	@NotEmpty(message = "O campo email não pode ser vazio")
	@Length(max = 200, message = "O nome deve ter no máximo 200 caracteres")
	@Email(message = "O email é inválido")
	private String email;
	
	@NotEmpty(message = "O campo senha não pode ser vazio")
	@Length(min = 4, max = 10, message = "A senha deve ter entre 4 e 10 caracteres")
	private String senha;
	
	public UsuarioDto() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "UsuarioDto [id=" + id + ", email=" + email + ", senha=" + senha + "]";
	}
}
