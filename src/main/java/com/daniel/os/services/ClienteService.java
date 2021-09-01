package com.daniel.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.os.domain.Cliente;
import com.daniel.os.domain.Pessoa;
import com.daniel.os.dtos.ClienteDTO;
import com.daniel.os.repositories.ClienteRepository;
import com.daniel.os.repositories.PessoaRepository;
import com.daniel.os.services.exceptions.DataIntegratyViolationException;
import com.daniel.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	//Busca cliente por id
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	//Busca clientes
	public Cliente create(ClienteDTO clienteDTO) {
		if (findByCPF(clienteDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		return clienteRepository.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
	}
	
	//Atualiza cliente
	public Cliente update(Integer id, ClienteDTO clienteDTO) {
		Cliente oldObj = findById(id);
		
		if (findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		oldObj.setNome(clienteDTO.getNome());
		oldObj.setCpf(clienteDTO.getCpf());
		oldObj.setTelefone(clienteDTO.getTelefone());
		
		return clienteRepository.save(oldObj);
	}
	
	//Deleta cliente
	public void delete(Integer id) {
		Cliente cliente = findById(id);
		
		if (cliente.getListaOrdemServico().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviço. Não pode ser deletado.");
		}
		
		clienteRepository.deleteById(id);
	}
	
	//Encontra cliente por CPF
	private Pessoa findByCPF(ClienteDTO clienteDTO) {
		Pessoa cliente = pessoaRepository.findByCPF(clienteDTO.getCpf());
			
		if (cliente != null) {
			return cliente;
		}		
		return null;
	}

}
