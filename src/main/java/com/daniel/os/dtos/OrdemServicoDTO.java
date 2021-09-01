package com.daniel.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.daniel.os.domain.OrdemServico;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrdemServicoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo Observações é obrigatório.")
	private String observacoes;
	
	private Integer status;
	private Integer tecnico;
	private Integer cliente;
	
	public OrdemServicoDTO() {
		super();
	}
	
	public OrdemServicoDTO(OrdemServico ordemServico) {
		super();
		this.id = ordemServico.getId();
		this.dataAbertura = ordemServico.getDataAbertura();
		this.dataFechamento = ordemServico.getDataFechamento();
		this.prioridade = ordemServico.getPrioridade().getCodigo();
		this.observacoes = ordemServico.getObservacoes();
		this.status = ordemServico.getStatus().getCodigo();
		this.tecnico = ordemServico.getTecnico().getId();
		this.cliente = ordemServico.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
}
