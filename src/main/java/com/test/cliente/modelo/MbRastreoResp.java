package com.test.cliente.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MbRastreoResp {

	private Integer nhits;
	private List<DatosUnidad> records;

	public MbRastreoResp() {
	}

	public Integer getNhits() {
		return nhits;
	}

	public void setNhits(Integer nhits) {
		this.nhits = nhits;
	}

	public List<DatosUnidad> getRecords() {
		return records;
	}

	public void setRecords(List<DatosUnidad> datos) {
		this.records = datos;
	}

	@Override
	public String toString() {
		return "MbRastreoResp [nhits=" + nhits + ", datos=" + records + "]";
	}
}
