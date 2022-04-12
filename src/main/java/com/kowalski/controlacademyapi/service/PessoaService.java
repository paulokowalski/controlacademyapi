package com.kowalski.controlacademyapi.service;

import com.kowalski.controlacademyapi.model.Pessoa;
import com.kowalski.controlacademyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
	
	private @Autowired PessoaRepository pessoaRepository;

	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
	
	public List<Pessoa> findAll(String nome){
		return pessoaRepository.findAll();
	}
	
	public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		pessoaRepository.save(pessoaSalva);
		return pessoaSalva;
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		if(!pessoaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva.get();
	}

}