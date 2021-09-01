package com.daniel.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.os.domain.Cliente;
import com.daniel.os.domain.OrdemServico;
import com.daniel.os.domain.Tecnico;
import com.daniel.os.domain.enuns.Prioridade;
import com.daniel.os.domain.enuns.Status;
import com.daniel.os.repositories.ClienteRepository;
import com.daniel.os.repositories.OrdemServicoRepository;
import com.daniel.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instanciaDB() {
		Tecnico tecnico1 = new Tecnico(null, "Daniel Matos", "973.549.980-01", "(12) 99248-3737");
		Tecnico tecnico2 = new Tecnico(null, "Cristiana Lemes", "745.393.660-84", "(12) 99187-7667");
		Cliente cliente1 = new Cliente(null, "Hellen Mayumi", "608.220.210-48", "(12) 99256-3735");

		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste criando OS", Status.ANDAMENTO, tecnico1, cliente1);

		tecnico1.getListaOrdemServico().add(os1);
		cliente1.getListaOrdemServico().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(tecnico1, tecnico2));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}

}
