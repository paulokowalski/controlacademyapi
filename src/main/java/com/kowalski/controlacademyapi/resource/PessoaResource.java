package com.kowalski.controlacademyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kowalski.controlacademyapi.event.RecursoCriadoEvent;
import com.kowalski.controlacademyapi.model.Pessoa;
import com.kowalski.controlacademyapi.repository.PessoaRepository;
import com.kowalski.controlacademyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaResource {
	
	private @Autowired PessoaRepository pessoaRepository;
	private @Autowired PessoaService pessoaService;
	private @Autowired ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_CONSULTAR_PESSOA') and #oauth2.hasScope('read')")
	public List<Pessoa> listarTodos(){
		return pessoaService.findAll();
	}
	
	@GetMapping("/consultar/{nome}")
	@PreAuthorize("hasAuthority('ROLE_CONSULTAR_PESSOA') and #oauth2.hasScope('read')")
	public List<Pessoa> consultarPeloNome(@PathVariable String nome){
		return pessoaService.findAll(nome);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CONSULTAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			return new ResponseEntity<Pessoa>(pessoaGet.get(), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<?> removerPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			pessoaRepository.deleteById(pessoaGet.get().getCodigo());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_EDITAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> editarPeloCodigo(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			return ResponseEntity.ok(pessoaService.atualizarPessoa(codigo, pessoa));
		}
		return ResponseEntity.notFound().build();
	}
}