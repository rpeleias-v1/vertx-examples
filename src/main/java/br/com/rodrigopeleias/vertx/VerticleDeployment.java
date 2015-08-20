package br.com.rodrigopeleias.vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class VerticleDeployment {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		DeploymentOptions options = new DeploymentOptions().setInstances(10);
		vertx.deployVerticle(MyFirstVerticle.class.getName(), options, response -> {
			if (response.succeeded()) {
				System.out.println("Deployment ID is :" + response.result());
				System.out.println("Verticle carregado com sucesso = " + MyFirstVerticle.class.getName());
				System.out.println();
			} else {
				System.out.println("Deployment failed!");
			}
		});
		
		vertx.deployVerticle(RandomWordVerticle.class.getName(), response -> {
			if (response.succeeded()) {
				System.out.println("Deployment ID is :" + response.result());
				System.out.println("Verticle carregado com sucesso = " + RandomWordVerticle.class.getName());
				System.out.println();
			} else {
				System.out.println("Deployment failed!");
			}
		});

	}

}
