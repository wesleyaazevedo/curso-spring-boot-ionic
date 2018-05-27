package com.cursomc.uml.domain;

import javax.persistence.Entity;

import com.cursomc.uml.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	
	public PagamentoComCartao() {
		//vazio
	}


	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numDeParcelas;
	}


	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}


	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	

}
