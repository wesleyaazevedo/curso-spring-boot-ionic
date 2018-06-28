package com.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	
	private int cod;
	private String descricao;
	
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//Pesquisa para retornar o tipo do ENUM (1-Pessao Fisica, 2-Pessoa Juridica)
		public static EstadoPagamento toEnum(Integer cod) {
			
			if (cod == null) {
				return null;
			}
			
			for (EstadoPagamento ep : EstadoPagamento.values()) {
				if (cod.equals(ep.getCod())) {
					return ep;
				}
			}
			
			throw new IllegalArgumentException("Id inválido "+ cod);
		
		}

}

