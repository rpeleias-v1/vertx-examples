package br.com.rodrigopeleias.vertx.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusReceptor extends AbstractVerticle {
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(EventBusReceptor.class.getName());
	}
	
	@Override
	public void start() throws Exception {
		EventBus eventBus = vertx.eventBus();
		
		eventBus.consumer("mensagem-request-response", mensagem -> {
			System.out.println("Recebi a seguinte mensagem no modelo Point-to-Point: " + mensagem.body());
			mensagem.reply("EventBusRecebedor, sua mensagem foi recebida com sucesso!");
		});
		System.out.println("EventBus pronto para receber mensagens!");
	}

}
