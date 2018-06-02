package com.kowalski.controlacademyapi.dao;

import java.util.LinkedList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kowalski.controlacademyapi.model.Pessoa;

@Mapper
public interface PessoaMapper {
	
	LinkedList<Pessoa> search(@Param("nome") String nome);
	
}
