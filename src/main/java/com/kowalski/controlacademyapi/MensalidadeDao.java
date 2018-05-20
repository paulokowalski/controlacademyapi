package com.kowalski.controlacademyapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.kowalski.controlacademyapi.model.Mensalidade;
import com.kowalski.controlacademyapi.repository.filter.MensalidadeFilter;

@Component
public class MensalidadeDao {

	private SqlSession sqlSession;
	
	public MensalidadeDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<Mensalidade> findAll(MensalidadeFilter mensalidadeFilter){
		Map<String, Object> params = new HashMap<>();
		if(Objects.nonNull(mensalidadeFilter.getCodigoPessoa())) {
			params.put("codigoPessoa", mensalidadeFilter.getCodigoPessoa());
		}
		
		if(Objects.nonNull(mensalidadeFilter.getMesVencimento())) {
			params.put("mesVencimento", mensalidadeFilter.getMesVencimento());
		}
		if(Objects.nonNull(mensalidadeFilter.getAnoVencimento())) {
			params.put("anoVencimento", mensalidadeFilter.getAnoVencimento());
		}
		if(Objects.nonNull(mensalidadeFilter.isLiquidadas())) {
			params.put("liquidadas", mensalidadeFilter.isLiquidadas());
		}
		return sqlSession.selectList("MensalidadeMapper.findAll", params);
	}
}