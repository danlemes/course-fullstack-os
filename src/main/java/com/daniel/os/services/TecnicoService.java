package com.daniel.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.os.domain.Pessoa;
import com.daniel.os.domain.Tecnico;
import com.daniel.os.dtos.TecnicoDTO;
import com.daniel.os.repositories.PessoaRepository;
import com.daniel.os.repositories.TecnicoRepository;
import com.daniel.os.services.exceptions.DataIntegratyViolationException;
import com.daniel.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	//Busca tecnico por id
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	//Busca tecnicos
	public Tecnico create(TecnicoDTO tecnicoDTO) {
		if (findByCPF(tecnicoDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		return tecnicoRepository.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}
	
	//Atualiza tecnico
	public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
		Tecnico oldObj = findById(id);
		
		if (findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		oldObj.setNome(tecnicoDTO.getNome());
		oldObj.setCpf(tecnicoDTO.getCpf());
		oldObj.setTelefone(tecnicoDTO.getTelefone());
		
		return tecnicoRepository.save(oldObj);
	}
	
	//Deleta tecnico
	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		
		if (tecnico.getListaOrdemServico().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui ordens de serviço. Não pode ser deletado.");
		}
		
		tecnicoRepository.deleteById(id);
	}
	
	//Encontra tecnico por CPF
	private Pessoa findByCPF(TecnicoDTO tecnicoDTO) {
		Pessoa tecnico = pessoaRepository.findByCPF(tecnicoDTO.getCpf());
			
		if (tecnico != null) {
			return tecnico;
		}		
		return null;
	}

}
