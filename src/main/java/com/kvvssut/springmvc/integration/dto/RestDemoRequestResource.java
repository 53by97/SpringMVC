package com.kvvssut.springmvc.integration.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class RestDemoRequestResource {
	
	@NotNull
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RestDemoRequestResource [amount=" + amount + "]";
	}
}
