package br.com.rodrigopeleias.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestAPIVerticle extends AbstractVerticle {

	private Map<Integer, Book> books = new LinkedHashMap<>();

	private void createSomeBooks() {
		Book cleanCode = new Book("Clean Code", "Uncle Bob", 500);
		Book designPatterns = new Book("Design Patterns", "GOF", 600);
		books.put(cleanCode.getId(), cleanCode);
		books.put(designPatterns.getId(), designPatterns);
	}

	@Override
	public void start(Future<Void> fut) throws Exception {
		createSomeBooks();

		Router router = Router.router(vertx);

		router.route("/").handler(
				routingContext -> {
					HttpServerResponse response = routingContext.response();
					response.putHeader("content-type", "text/html").end(
							"<h1>Vertx application</h1>");
				});

		// Roteamento para a aplicação vertx servir conteúdo estático
		router.route("/assets/*").handler(StaticHandler.create("assets"));

		// RESTful API
		router.get("/api/books").handler(this::getAll);
		router.route("/api/books*").handler(BodyHandler.create());
		router.post("/api/books").handler(this::addOne);
		router.get("/api/books/:id").handler(this::getOne);
		router.put("/api/books/:id").handler(this::updateOne);
		router.delete("/api/books/:id").handler(this::deleteOne);

		vertx.createHttpServer().requestHandler(router::accept)
				.listen(config().getInteger("http.port", 8080), result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}

	private void getAll(RoutingContext routingContext) {
		routingContext.response()
				.putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(books.values()));
		;
	}

	private void addOne(RoutingContext routingContext) {
		final Book book = Json.decodeValue(routingContext.getBodyAsString(),
				Book.class);
		books.put(book.getId(), book);
		routingContext.response().setStatusCode(201)
				.putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(book));
	}

	private void getOne(RoutingContext routingContext) {
		final String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			final Integer idAsInteger = Integer.valueOf(id);
			Book book = books.get(idAsInteger);
			if (book == null) {
				routingContext.response().setStatusCode(400).end();
			} else {
				routingContext
						.response()
						.putHeader("content-type",
								"application/json; charset=utf-8")
						.end(Json.encodePrettily(book));
			}
		}
	}

	private void deleteOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id); 
			books.remove(idAsInteger);
		}
		routingContext.response().setStatusCode(204).end();
	}

	private void updateOne(RoutingContext routingContext) {
		final String id = routingContext.request().getParam("id");
		JsonObject json = routingContext.getBodyAsJson();
		if (id == null || json == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			final Integer idAsInteger = Integer.valueOf(id);
			Book book = books.get(idAsInteger);
			if (book == null) {
				routingContext.response().setStatusCode(400).end();
			} else {
				book.setName(json.getString("name"));
				book.setAuthor(json.getString("author"));
				book.setNumberOfPages(Integer.parseInt(json.getString("numberOfPages")));
				routingContext
						.response()
						.putHeader("content-type",
								"application;json; charset=utf-8")
						.end(Json.encodePrettily(book));
			}
		}
	}

}
