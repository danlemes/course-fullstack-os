package com.daniel.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.os.dtos.OrdemServicoDTO;
import com.daniel.os.services.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordemservico")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoService	ordemServicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id) {
		OrdemServicoDTO osDTO = new OrdemServicoDTO(ordemServicoService.findById(id));
		return ResponseEntity.ok().body(osDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> listOsDTO = ordemServicoService.findAll().stream().map(obj -> new OrdemServicoDTO(obj)).collect(Collectors.toList());		
		return ResponseEntity.ok().body(listOsDTO);
	}
	
	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO ordemServicoDTO) {
		ordemServicoDTO = new OrdemServicoDTO(ordemServicoService.create(ordemServicoDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ordemServicoDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServicoDTO osDTO) {
		osDTO = new OrdemServicoDTO(ordemServicoService.update(osDTO));
		return ResponseEntity.ok().body(osDTO);
	}
}
