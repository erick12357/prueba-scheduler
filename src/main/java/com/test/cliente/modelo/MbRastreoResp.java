package com.test.cliente.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MbRastreoResp {
	
	private Integer nhits;

	public MbRastreoResp() {
	}

	public Integer getNhits() {
		return nhits;
	}

	public void setNhits(Integer nhits) {
		this.nhits = nhits;
	}
}
