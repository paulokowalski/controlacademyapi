package com.kowalski.controlacademyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kowalski.controlacademyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}