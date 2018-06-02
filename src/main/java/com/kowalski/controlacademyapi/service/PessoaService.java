package com.kowalski.controlacademyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kowalski.controlacademyapi.dao.PessoaMapper;
import com.kowalski.controlacademyapi.model.Pessoa;
import com.kowalski.controlacademyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	private @Autowired PessoaRepository pessoaRepository;
	private @Autowired PessoaMapper mapper;
	
	public List<Pessoa> findAll(){
		return mapper.search(null);
	}
	
	public List<Pessoa> findAll(String nome){
//		return mapper.search("%" + nome + "%");
		return mapper.search(nome);
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