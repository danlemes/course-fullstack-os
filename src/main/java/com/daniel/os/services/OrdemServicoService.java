package com.daniel.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.os.domain.Cliente;
import com.daniel.os.domain.OrdemServico;
import com.daniel.os.domain.Tecnico;
import com.daniel.os.domain.enuns.Prioridade;
import com.daniel.os.domain.enuns.Status;
import com.daniel.os.dtos.OrdemServicoDTO;
import com.daniel.os.repositories.OrdemServicoRepository;
import com.daniel.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository osRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> ordemServico = osRepository.findById(id);
		return ordemServico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}
	
	public List<OrdemServico> findAll() {
		return osRepository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO ordemServicoDTO) {		
		return fromDTO(ordemServicoDTO);
	}
	
	public OrdemServico update(@Valid OrdemServicoDTO osDTO) {
		findById(osDTO.getId());
		return fromDTO(osDTO);
	}
	
	private OrdemServico fromDTO(OrdemServicoDTO osDTO) {
		OrdemServico os = new OrdemServico();
		os.setId(osDTO.getId());
		os.setObservacoes(osDTO.getObservacoes());
		os.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
		os.setStatus(Status.toEnum(osDTO.getStatus()));
		
		Tecnico tecnico = tecnicoService.findById(osDTO.getTecnico());		
		Cliente cliente = clienteService.findById(osDTO.getCliente());
		
		os.setTecnico(tecnico);
		os.setCliente(cliente);
		
		if (os.getStatus().getCodigo().equals(2)) {
			os.setDataFechamento(LocalDateTime.now());
		}
		
		return osRepository.save(os);
	}


}
