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

import com.daniel.os.domain.Tecnico;
import com.daniel.os.dtos.TecnicoDTO;
import com.daniel.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	// EndPoint -> localhost:8080/tecnicos/
	
	@Autowired
	private TecnicoService tecnicoService;
	
	//Pega Tecnico por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
		return ResponseEntity.ok().body(tecnicoDTO);
	}
	
	//Pega Tecnicos
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		
//		Opção 1:
//		List<Tecnico> list = tecnicoService.findAll();
//		List<TecnicoDTO> listDTO = new ArrayList<>();
//		
//		for (Tecnico obj : list) {
//			listDTO.add(new TecnicoDTO(obj));
//		}
//		
//		Opção 2:
//		list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
		
//		Opção 3:
		List<TecnicoDTO> listDTO = tecnicoService.findAll().stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	//Cria Tecnico
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		
		Tecnico tecnico = tecnicoService.create(tecnicoDTO);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//Atualiza Tecnico
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO) {
		TecnicoDTO newObjDTO = new TecnicoDTO(tecnicoService.update(id, tecnicoDTO));		
		return ResponseEntity.ok().body(newObjDTO);
	}
		
	//Deleta Tecnico
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
