package br.com.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.pedidovenda.modelo.Pedido;
import br.com.pedidovenda.modelo.StatusPedido;
import br.com.pedidovenda.repository.Pedidos;
import br.com.pedidovenda.util.jpa.Transactional;


public class CadastroPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido){
		if(pedido.isNovo()){
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.recalcularValorTotal();
		
		if(pedido.isNaoAlteravel()){
			throw new NegocioException("Pedido n�o pode ser alterado no status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		if(pedido.getItens().isEmpty()){
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if(pedido.isValorTotalNegativo()){
			throw new NegocioException("Valor total do pedido n�o pode ser negativo.");
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}

}
