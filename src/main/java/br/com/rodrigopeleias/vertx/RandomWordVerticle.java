package br.com.rodrigopeleias.vertx;

import io.vertx.core.AbstractVerticle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomWordVerticle extends AbstractVerticle{

	private List<String> listaDePalavras = Arrays.asList("Corinthians", "Flamengo", "Palmeiras", "Santos", "Vasco");
	
	@Override
	public void start() throws Exception {		
		vertx.createHttpServer()
			.requestHandler(r-> {				
				r.response()
					.putHeader("content-type", "text/html")
					.end("<h1>O time sorteado dentro do grupo " + listaDePalavras.toString() + " "
							+ "foi: <br/> <b>" + this.generateRandom());
		}).listen(8081);
		
	}
	
	private String generateRandom() {
		Random random = new Random();
		int indexSorteado = random.nextInt(listaDePalavras.size());
		return listaDePalavras.get(indexSorteado);
	}
}
