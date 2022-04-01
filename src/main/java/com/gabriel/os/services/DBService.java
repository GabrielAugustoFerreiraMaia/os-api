package com.gabriel.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Cliente;
import com.gabriel.os.domain.OS;
import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.domain.enuns.Prioridade;
import com.gabriel.os.domain.enuns.Status;
import com.gabriel.os.repositories.ClienteRepository;
import com.gabriel.os.repositories.OSRepository;
import com.gabriel.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;
	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Gabriel Augusto", "001.475.480-04", "(88)98888-8888");
		Tecnico t2 = new Tecnico(null, "Augusto Gabriel", "696.346.560-64", "(88)98888-6666");

		Cliente c1 = new Cliente(null, "Betina", "856.472.760-91", "(88)98888-7777");
 
		OS os1 = new OS(null, Prioridade.ALTA, "TESTE", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);

		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2));

		clienteRepository.saveAll(Arrays.asList(c1));

		osRepository.saveAll(Arrays.asList(os1));
	}
	
}
