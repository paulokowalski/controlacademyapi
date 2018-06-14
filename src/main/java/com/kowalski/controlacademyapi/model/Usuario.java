package com.kowalski.controlacademyapi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
@Data
public class Usuario implements Serializable {

	private static final long serialVersionUID = 9065828045288285041L;
	
	@Id
	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_permissao", joinColumns=@JoinColumn(name="codigo_usuario"), inverseJoinColumns=@JoinColumn(name="codigo_permissao"))
	private List<Permissao> permissoes;

}