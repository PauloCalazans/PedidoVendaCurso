package br.com.pedidovenda.modelo;

public enum StatusPedido {
	
	ORCAMENTO("Or�amento"), 
	EMITIDO("Emitido"), 
	CANCELADO("Cancelado");
	
	private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	
}
