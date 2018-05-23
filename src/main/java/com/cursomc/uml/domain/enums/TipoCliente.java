package com.cursomc.uml.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;
	
	//Contrutor TipoCliente
	private TipoCliente (int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String descricao(){ 
		return descricao;
	}
	
	//Pesquisa para retornar o tipo do ENUM (1-Pessao Fisica, 2-Pessoa Juridica)
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoCliente tc : TipoCliente.values()) {
			if (cod.equals(tc.getCod())) {
				return tc;
			}
		}
		
		throw new IllegalArgumentException("Id inválido "+ cod);
	
	}
	
	
	
}
