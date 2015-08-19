package br.com.rodrigopeleias.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyFirstVerticle extends AbstractVerticle{
	
	@Override
	public void start() throws Exception {
		vertx.createHttpServer()
		.requestHandler(r -> {
			r.response().end("<h1>Meu primeiro Verticle em Java, especialmente para o Meetup Java :)");
		})
		.listen(8080);
	}

}
