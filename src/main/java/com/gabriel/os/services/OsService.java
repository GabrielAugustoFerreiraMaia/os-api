package com.gabriel.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.os.domain.Cliente;
import com.gabriel.os.domain.OS;
import com.gabriel.os.domain.Tecnico;
import com.gabriel.os.domain.enuns.Prioridade;
import com.gabriel.os.domain.enuns.Status;
import com.gabriel.os.dtos.OSDTO;
import com.gabriel.os.repositories.OSRepository;
import com.gabriel.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontado! Id: " + id + " , Tipo: " + OS.class.getName()));
	}
	
	
	public List<OS> findAll(){
		return repository.findAll();
	}


	public OS create(@Valid OSDTO obj) {
		return fromDto(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDto(obj);
	}
	
	private OS fromDto(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}


	
}
	