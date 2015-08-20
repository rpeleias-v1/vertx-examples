package br.com.rodrigopeleias.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> future) throws Exception {
		vertx.deployVerticle(RandomNumberVerticle.class.getName(), response -> {
			if (response.succeeded()) {
				future.complete();
				System.out.println("Verticle carregado com sucesso = " + RandomNumberVerticle.class.getName());
			} else {
				future.fail("O Verticle para geração de número aleatório falhou");
			}
		});							
	}

}
