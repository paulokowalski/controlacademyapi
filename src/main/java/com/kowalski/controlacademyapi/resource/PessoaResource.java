package com.kowalski.controlacademyapi.resource;

import com.kowalski.controlacademyapi.event.RecursoCriadoEvent;
import com.kowalski.controlacademyapi.model.Pessoa;
import com.kowalski.controlacademyapi.repository.PessoaRepository;
import com.kowalski.controlacademyapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	private @Autowired PessoaRepository pessoaRepository;
	private @Autowired PessoaService pessoaService;
	private @Autowired ApplicationEventPublisher publisher;

	@GetMapping
	public List<Pessoa> listarTodos(){
		return pessoaService.findAll();
	}
	
	@GetMapping("/consultar/{nome}")
	public List<Pessoa> consultarPeloNome(@PathVariable String nome){
		return pessoaService.findAll(nome);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			return new ResponseEntity<>(pessoaGet.get(), HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> removerPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			pessoaRepository.deleteById(pessoaGet.get().getCodigo());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> editarPeloCodigo(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Optional<Pessoa> pessoaGet = pessoaRepository.findById(codigo);
		if(pessoaGet.isPresent()) {
			return ResponseEntity.ok(pessoaService.atualizarPessoa(codigo, pessoa));
		}
		return ResponseEntity.notFound().build();
	}
}