def eventBus = vertx.eventBus();

vertx.setPeriodic(2000, { response -> 
  eventBus.publish("mensagem-request-response", "Mensagem enviada em Groovy no modelo Publish-Sbuscribe!");
});
