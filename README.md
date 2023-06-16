# eazylogg-server
Server Side do projeto EazyLogg

## O projeto
O Eazylogg é um projeto criado para portfólio.


## Publico
O serviço:

  * O projeto implementa uma aplicação de entregas, onde lojistas podem conseguir entregadores que atendam suas necessidades, e entregadores podem conseguir entregas com os lojistas.
  * O App permite que lojistas contratem entregadores que atuem de bicicleta, moto, carro de todos os portes, furgões e caminhonetes, podendo ser contrato por número de pacotes ou por período de tempo.


## Construção e parte técnica
O serviço é dividido em:

  * Server side: Parte do backend, onde uma API será responsável por armazenar e transitar as informações.
  * Client side: Parte visível ao usuário onde será possível fazer cadastros, ver catálogos e acompanhar as entregas via interface de usuário. O Client Side é composto por Aplicação Web com Landing Page e Plataforma responsiva para multiplos dispositivos, e uma Aplicação Mobile híbrida para Android e IOS. A plataforma é responsável por gerenciar os perfis de cada tipo de usuário (Admins, Empresas e Entregadores), e entregar as informações conforme o grau hierárquico dos usuarios.


## UML

<img src="https://github.com/edderluanps/eazylogg-server/blob/3b973a60d6070d81ee9d51ae879f674ca59eeea3/assets/uml.png">


## Tecnologias

### Backend

  * Java 17
  * Astah UML
  * Springboot
  * JPA/Hibernate
  * Lombok
  * JUnit
  * Mockito
  * OpenPDF
  * OAuth
  * JWT
  * Docker
  * Swagger

### Banco de dados

  * H2 Database
  * PostgreSQL

### Deploy
  
  * Backend: [...]
  * DB: [...]


## Instalação Passo a passo

* Obs: O processo descrito priorizará o MS Windows.

### Java e Spring
 * Baixe e instale o Java, recomendo a versão 17. Clique <a href="https://www.oracle.com/br/java/technologies/downloads/#jdk17-windows" target="_blank" rel="noopener noreferrer"> aqui </a> para baixar;
 * Agora, utilize uma IDE para abrir o projeto, sugiro a mesma que utilizei: Apache NetBeans IDE 15. Baixe clicando <a href="https://netbeans.apache.org/download/nb15/" target="_blank" rel="noopener noreferrer"> aqui </a>;
 * Caso queira optar por outra IDE, <a href="https://spring.io/tools" target="_blank" rel="noopener noreferrer"> Spring Tool Suite </a> e <a href="https://www.jetbrains.com/pt-br/idea/" target="_blank" rel="noopener noreferrer"> Intellij IDEA </a> são excelentes;
 * Em caso de dúvidas na configuração das IDEs, consulte a documentação dos mesmos ou Google/YouTube;
 * Com o ambiente pronto, baixe a pasta Backend como arquivo zip, entre em uma das IDEs selecionadas e importe o projeto;
 * Caso não saiba importar, clique <a href="https://itsnatrivera.wordpress.com/2017/04/28/how-to-import-a-project-in-netbeans/" target="_blank" rel="noopener noreferrer">aqui</a> para aprender o passo a passo. Créditos ao autor;


### Banco de dados PostgreSQL
 * É preciso ter o Postgres e um gerenciador do banco instalado na máquina, seja o PG Admin ou DBeaver;
 * Instalação do PG Admin <a href="https://www.youtube.com/watch?v=L_2l8XTCPAE&t=624s">aqui</a>. Créditos ao autor;
 * Instalação do DBeaver <a href="https://www.youtube.com/watch?v=i0gY3HePe-k">aqui</a>. Créditos ao autor;


 ## Licença

Copyright © 2023 <a href="https://github.com/edderluanps">Edder Luan</a>

Este projeto está sob licença MIT.
