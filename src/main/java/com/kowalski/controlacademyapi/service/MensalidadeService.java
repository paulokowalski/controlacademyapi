package com.kowalski.controlacademyapi.service;

import com.kowalski.controlacademyapi.dao.MensalidadeMapper;
import com.kowalski.controlacademyapi.model.Mensalidade;
import com.kowalski.controlacademyapi.model.Pessoa;
import com.kowalski.controlacademyapi.repository.MensalidadeRepository;
import com.kowalski.controlacademyapi.repository.PessoaRepository;
import com.kowalski.controlacademyapi.repository.filter.MensalidadeFilter;
import com.kowalski.controlacademyapi.service.exception.MensalidadeJaPagaException;
import com.kowalski.controlacademyapi.service.exception.PessoaInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MensalidadeService {
	
	private @Autowired MensalidadeRepository mensalidadeRepository;
	private @Autowired PessoaRepository pessoaRepository;
	private @Autowired MensalidadeMapper mensalidadeMapper;
	
	public List<Mensalidade> pesquisar(MensalidadeFilter mensalidadeFilter){
		return mensalidadeMapper.findAll(mensalidadeFilter);
	}

	public Mensalidade buscarPessoaPeloCodigo(Long codigo) {
		Optional<Mensalidade> mensalidadeSalva = mensalidadeRepository.findById(codigo);
		if(!mensalidadeSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return mensalidadeSalva.get();
	}
	
	public Mensalidade efetuarPagamento(Long codigo, LocalDate dataPagamento) {
		Mensalidade mensalidade = validar(codigo);
		mensalidade.setDataPagamento(dataPagamento);
		mensalidade = mensalidadeRepository.save(mensalidade);
		return mensalidade;
	}
	
	public void removerPagamento(Long codigo) {
		Mensalidade mensalidade = validar(codigo);
		mensalidadeRepository.delete(mensalidade);
	}
	
	private Mensalidade validar(Long codigo) {
		Mensalidade mensalidade = buscarPessoaPeloCodigo(codigo);
		if(mensalidade.isLiquidada()) {
			throw new MensalidadeJaPagaException();
		}
		Pessoa pessoa = pessoaRepository.getOne(mensalidade.getPessoa().getCodigo());
		if(pessoa.isInativa()) {
			throw new PessoaInativaException();
		}
		
		return mensalidade;
	}
}