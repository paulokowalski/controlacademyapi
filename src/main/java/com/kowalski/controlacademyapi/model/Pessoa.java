package com.kowalski.controlacademyapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="pessoa")
@Data
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = -6871657998331177465L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Size(min=3, max=50)
	@NotNull
	private String nome;
	
	@NotNull
	@Size(min=11, max=11)
	private String cpf;
	
	@NotNull
	private LocalDate dataNascimento;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private Boolean ativo;
	
	@Embedded
	private Endereco endereco;
	
	public String getNome() {
		if(Strings.isBlank(nome)) {
			return nome;
		}
		return nome.toUpperCase();
	}

	@JsonIgnore
	@Transient
	public boolean isInativa() {
		return !this.ativo;
	}
}