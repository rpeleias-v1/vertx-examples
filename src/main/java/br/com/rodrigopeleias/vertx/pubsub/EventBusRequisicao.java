package br.com.rodrigopeleias.vertx.pubsub;

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
			eventBus.publish("mensagem-request-response", "Mensagem enviada em Java, por Publish-Subscribe!");
		});
	}
}
