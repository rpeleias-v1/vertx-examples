package br.com.rodrigopeleias.vertx.pubsub;

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
			System.out.println("Recebi a seguinte mensagem no modelo Publish-Subscribe =  " + mensagem.body());			
		});
		System.out.println("EventBus pronto para receber mensagens!");
	}

}
