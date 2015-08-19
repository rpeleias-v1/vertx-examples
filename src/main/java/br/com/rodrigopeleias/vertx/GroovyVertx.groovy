vertx.createHttpServer().requestHandler({ req ->
  req.response()
    .putHeader("content-type", "text/html")
    .end("<h1>Meetup Java, eu sou um programa desenvolvido em Vert.x escrito em Groovy!</h1>");
}).listen(8080);
