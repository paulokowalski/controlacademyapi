package com.kowalski.controlacademyapi.resource;

import com.kowalski.controlacademyapi.event.RecursoCriadoEvent;
import com.kowalski.controlacademyapi.model.Mensalidade;
import com.kowalski.controlacademyapi.repository.MensalidadeRepository;
import com.kowalski.controlacademyapi.repository.filter.MensalidadeFilter;
import com.kowalski.controlacademyapi.service.MensalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeResource {
	
	private @Autowired MensalidadeService mensalidadeService;
	private @Autowired ApplicationEventPublisher publisher;
	private @Autowired MensalidadeRepository mensalidadeRepository;

	@GetMapping
	public List<Mensalidade> pesquisar(MensalidadeFilter mensalidadeFilter) {
		return mensalidadeService.pesquisar(mensalidadeFilter);
	}
	
	@PutMapping("{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void efetuarPagamentoMensalidade(@PathVariable Long codigo, @RequestBody LocalDate dataPagamento) {
		mensalidadeService.efetuarPagamento(codigo, dataPagamento);
	}
	
	@PostMapping
	public ResponseEntity<Mensalidade> salvarMensalidade(@Valid @RequestBody Mensalidade mensalidade, HttpServletResponse response) {
		Mensalidade mensalidadeSalva = mensalidadeRepository.save(mensalidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, mensalidadeSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(mensalidadeSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPeloCodigo(@PathVariable Long codigo) {
		mensalidadeService.removerPagamento(codigo);
	}
}