package br.com.rodrigopeleias.vertx;

import java.util.LinkedHashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.*;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyFirstVerticle extends AbstractVerticle{
	
	private Map<Integer, Whisky> products = new LinkedHashMap<>();
	
	private void createSomeWhiskies() {
		Whisky blackLabel = new Whisky("Black Label", "Scotland");
		Whisky blueLabel = new Whisky("Blue Label", "USA");
		products.put(blackLabel.getId(), blackLabel);
		products.put(blueLabel.getId(), blueLabel);
	}
	
	@Override
	public void start(Future<Void> fut) throws Exception {
		
		createSomeWhiskies();
		Router router = Router.router(vertx);
		
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response
				.putHeader("content-type", "text/html")
				.end("<h1>Hello from my first Vert.x 3 application</h1>");
		});
		
		router.route("/assets/*").handler(StaticHandler.create("assets"));
		
		//RESTful API
		router.route("/api/whiskes*").handler(BodyHandler.create());
		router.get("/api/whiskies").handler(this::getAll);
		router.post("/api/whiskies").handler(this::addOne);
		router.delete("/api/whiskies/:id").handler(this::deleteOne);
		
		vertx.createHttpServer()
		.requestHandler(router::accept)		
		.listen(config().getInteger("http.port", 8080), 
		result -> {
			if (result.succeeded()) {
				fut.complete();
			} else {
				fut.fail(result.cause());
			}
		});
	}
	
	private void getAll(RoutingContext routingContext) {
		routingContext.response()
			.putHeader("content-type", "application/json; charset=utf-8" )
			.end(Json.encodePrettily(products.values()));;
	}
	
	private void addOne(RoutingContext routingContext) {
		final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(), Whisky.class);
		products.put(whisky.getId(), whisky);
		routingContext.response()
			.setStatusCode(201)
			.putHeader("content-type", "application/json; charset=utf-8")
			.end(Json.encodePrettily(whisky));
	}
	
	private void deleteOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id);
			products.remove(idAsInteger);
		}
		routingContext.response().setStatusCode(204).end();
	}

}
