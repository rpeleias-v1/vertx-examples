package br.com.rodrigopeleias.vertx;

import io.vertx.core.AbstractVerticle;

import java.util.Random;

public class RandomNumberVerticle extends AbstractVerticle{

	@Override
	public void start() throws Exception {		
		vertx.createHttpServer()
			.requestHandler(r-> {				
				r.response()
					.putHeader("content-type", "text/html")
					.end("<h1>Numero gerado: " + this.generateRandom());
		}).listen(8080);
		
	}
	
	private Integer generateRandom() {
		Random random = new Random();
		return random.nextInt();
	}
}
