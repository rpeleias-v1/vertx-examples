Projeto para Demonstração do framework Vert.x no Meetup Java, em 25/08/2015.

O Vert.x é um framework voltado ao desenvolvimento de aplicações assíncronas, orientada a eventos e poliglotas (desenvolvimento da aplicação com a linguagem de programação de sua preferência)

Com o Vert.x, é possível o desenvolvimento de aplicações REST, Web, Reativas, Orientadas a Testes e muito mais. 

Os slides da palestra estão disponíveis em <a href="http://www.slideshare.net/rpeleias/desenvolvimento-de-aplicaes-assncronas-orientadas-a-eventos-e-poliglotas-com-vertx"><b>http://www.slideshare.net/rpeleias/desenvolvimento-de-aplicaes-assncronas-orientadas-a-eventos-e-poliglotas-com-vertx</b></a>

Mais informações sobre o Vert.x e toda a suas principais referências disponíveis em <a href="http://vertx.io/"><b>http://vertx.io/</b></a>

Este projeto contém 5 branchs com os exemplos demonstrados no evento Meetup Java. Abaixo seguem breves descrições e como executar cada um dos exemplos (na respectiva ordem de execução no momento da palestra)


Branch 1: <b>demo-1-first-verticles</b>

Execução através do terminal: <b>vertx run MyFirstVerticle.java</b>
A mesma execução se aplica aos exemplos desenvolvidos em <b>.groovy</b> e <b>.js</b>
Para a execução do projeto através do Maven, executar no terminal o comando <b>mvn clean verify -DskipTests</b>


Branch 2: <b>demo-2-multiple-verticles</b>

Execução através do terminal: <b>vertx run MyFirstVerticle.java</b>

Execução da classe <b>VerticleDeployment.java</b> através to terminal ou Eclipse como uma classe Java comum: os verticles estão declarados dentro do método <b>main</b> 

Para a execução do projeto através do Maven, executar no terminal o comando <b>mvn clean verify -DskipTests</b>


Branch 3: <b>demo-3-eventbus</b>

Execução do Verticle o recebimento e troca de mensagens entre EventBus e os Verticles: <b>vertx run EventBusReceptor.java -cluster</b> 

Execução dos Verticles em Java, JavaScript e Groovy para estabeler conexão e troca de mensagens com o EventBus: <b>vertx run EventBusRequisicao.java -cluster</b>(e suas versões <b>.groovy</b> e <b>.js</b> em outros terminais) 

IMPORTANTE: atenção ao parâmetro -cluster: caso não executar o vertx com este parâmetro, a troca de mensagens entre EventBus e verticles não acontecerá


Branch 4: <b>demo-4-rest-api</b>

Execução através do terminal: <b>vertx run RestAPIVerticle.java</b>

Para a execução do projeto através do Maven, executar no terminal o comando <b>mvn clean verify -DskipTests</b>

Para o acesso da operação GET para a visualização de todos os livros, acessar o link http://localhost:8080/api/books


Branch 5: <b>demo-5-rest-web</b>

Para a execução do projeto através do Maven, executar no terminal o comando <b>mvn clean verify -DskipTests</b>
Esta branch será executada somente através do Maven por causa dos recursos estáticos, como páginas HTML, e arquivos css e JavaScript

Para o acesso do projeto web, acessar o link http://localhost:8080/assets/index.html

Acesso do projeto Web através do Heroku: <a href="http://evening-temple-8982.herokuapp.com/assets/index.html">http://evening-temple-8982.herokuapp.com/assets/index.html</a>
