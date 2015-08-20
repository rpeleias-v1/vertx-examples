def eventBus = vertx.eventBus();

vertx.setPeriodic(2000, { response -> 
  eventBus.send("mensagem-request-response", "Mensagem enviada em Groovy!", { reply -> 
    if (reply.succeeded()) {
	println "Recebida a resposta no código Groovy = " + reply.result().body()
    }
    else {
	println "Sem resposta!"
    }
  });
});
