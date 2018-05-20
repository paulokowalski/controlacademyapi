package com.kowalski.controlacademyapi.repository.filter;

public class MensalidadeFilter {
	
	private Long codigoPessoa;
	private Integer mesVencimento;
	private Integer anoVencimento;
	private Boolean liquidadas;

	public Long getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Long codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public Integer getMesVencimento() {
		return mesVencimento;
	}

	public void setMesVencimento(Integer mesVencimento) {
		this.mesVencimento = mesVencimento;
	}

	public Integer getAnoVencimento() {
		return anoVencimento;
	}

	public void setAnoVencimento(Integer anoVencimento) {
		this.anoVencimento = anoVencimento;
	}

	public Boolean isLiquidadas() {
		return liquidadas;
	}

	public void setLiquidadas(Boolean liquidadas) {
		this.liquidadas = liquidadas;
	}
}