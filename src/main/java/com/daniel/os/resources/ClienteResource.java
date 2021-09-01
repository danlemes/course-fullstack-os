package com.daniel.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.os.domain.Cliente;
import com.daniel.os.dtos.ClienteDTO;
import com.daniel.os.services.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	// EndPoint -> localhost:8080/clientes/
	
	@Autowired
	private ClienteService clienteService;
	
	//Pega cliente por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO clienteDTO = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(clienteDTO);
	}
	
	//Pega clientes
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {		
		List<ClienteDTO> listDTO = clienteService.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(listDTO);
	}
	
	//Cria cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO) {
		
		Cliente cliente = clienteService.create(clienteDTO);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//Atualiza cliente
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO newObjDTO = new ClienteDTO(clienteService.update(id, clienteDTO));		
		return ResponseEntity.ok().body(newObjDTO);
	}
		
	//Deleta cliente
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
