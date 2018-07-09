package com.eduardotanaka.netflix.api.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eduardotanaka.netflix.api.enums.PerfilEnum;

@Entity
@Table(name="TB_USER")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -2542246530727792447L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USER", nullable=false)
	private Long id;
	
	@Column(name="DS_EMAIL", nullable=false, unique=true)
	private String email;
	
	@Column(name="DS_SENHA", nullable=false)
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="ID_PERFIL", nullable=false)
	private PerfilEnum perfil;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CRIACAO", nullable=false)
	private Calendar dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ATUALIZACAO", nullable=false)
	private Calendar dataAtualizacao;
	
	public Usuario() {}

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

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", perfil=" + perfil + "]";
	}
}
