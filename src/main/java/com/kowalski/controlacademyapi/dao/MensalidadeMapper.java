package com.kowalski.controlacademyapi.dao;

import com.kowalski.controlacademyapi.model.Mensalidade;
import com.kowalski.controlacademyapi.repository.filter.MensalidadeFilter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MensalidadeMapper {

    List<Mensalidade> findAll(MensalidadeFilter mensalidadeFilter);
}
