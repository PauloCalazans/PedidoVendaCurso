package br.com.pedidovenda.controller;

import br.com.pedidovenda.modelo.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;

	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

}
