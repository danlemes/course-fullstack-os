package com.daniel.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;
import com.daniel.os.domain.Tecnico;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	
	@NotEmpty(message = "É obrigatório o preenchimento do campo Nome.")
	private String nome;	
	
	@CPF
	@NotEmpty(message = "É obrigatório o preenchimento do campo CPF.")
	private String cpf;	
	
	@NotEmpty(message = "É obrigatório o preenchimento do campo Telefone.")
	private String telefone;
	
	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
