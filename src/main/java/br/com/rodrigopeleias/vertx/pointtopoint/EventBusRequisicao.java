package br.com.rodrigopeleias.vertx.pointtopoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventBusRequisicao extends AbstractVerticle{

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(EventBusRequisicao.class.getName());		
	}
	
	@Override
	public void start() throws Exception {
		EventBus eventBus = vertx.eventBus();
		
		vertx.setPeriodic(2000, response -> {
			eventBus.send("mensagem-request-response", "Mensagem enviada em Java!", reply -> {
				if (reply.succeeded()) {
					System.out.println("Recebida a resposta = " + reply.result().body());
				} else {
					System.out.println("Sem resposta!!");
				}
			});
		});
	}
}
