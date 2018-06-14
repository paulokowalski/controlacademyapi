package com.kowalski.controlacademyapi.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
@Data
public class Permissao implements Serializable {

	private static final long serialVersionUID = 794524689397310091L;

	@Id
	private Long codigo;
	private String descricao;
}