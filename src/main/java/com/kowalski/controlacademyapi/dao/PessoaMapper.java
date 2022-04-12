package com.kowalski.controlacademyapi.dao;

import com.kowalski.controlacademyapi.model.Pessoa;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;

@Mapper
public interface PessoaMapper {

//	@Select("select * from pessoa p where upper(p.nome) like upper('%${nome}%')")
	LinkedList<Pessoa> search(@Param("nome") String nome);

}
