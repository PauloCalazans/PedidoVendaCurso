package br.com.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.com.pedidovenda.modelo.Pedido;
import br.com.pedidovenda.util.jsf.FacesUtil;
import br.com.pedidovenda.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	@Inject
	@PedidoEdicao
	private Pedido pedido;

	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();

		message.to(this.pedido.getCliente().getEmail())
			.subject("Pedido " + this.pedido.getId())
			.bodyHtml("<strong>Valor total:</strong> " + this.pedido.getValorTotal())
			.send();
			
		FacesUtil.addInfoMessage("Pedido enviado por email enviado com sucesso!");
	}
}
