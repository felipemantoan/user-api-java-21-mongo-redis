# Teste para analista desenvolvedor 

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=felipemantoan_user-api-java-21-mongo-redis&metric=coverage)](https://sonarcloud.io/summary/new_code?id=felipemantoan_user-api-java-21-mongo-redis)
[![codecov](https://codecov.io/gh/felipemantoan/user-api-java-21-mongo-redis/graph/badge.svg?token=V8A35IRJEO)](https://codecov.io/gh/felipemantoan/user-api-java-21-mongo-redis)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=felipemantoan_user-api-java-21-mongo-redis&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=felipemantoan_user-api-java-21-mongo-redis)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=felipemantoan_user-api-java-21-mongo-redis&metric=bugs)](https://sonarcloud.io/summary/new_code?id=felipemantoan_user-api-java-21-mongo-redis)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=flat&logo=githubactions&logoColor=white) 
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=flat&logo=redis&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=flat&logo=spring&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=flat&logo=mongodb&logoColor=white) ![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=flat&logo=swagger&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=flat&logo=docker&logoColor=white)

Olá, obrigado pelo interesse em fazer parte da nossa equipe.

O objetivo deste teste é verificar (até certo ponto) suas habilidades de codificação e arquitetura. Para isso você receberá um problema simples onde poderá mostrar suas técnicas de desenvolvimento.

Nós encorajamos você a exagerar um pouco na solução para mostrar do que você é capaz.

Considere um cenário em que você esteja construindo uma aplicação pronta para produção, onde outros desenvolvedores precisarão trabalhar e manter essa aplicação ao longo do tempo.

Você **PODE** e **DEVE** usar bibliotecas de terceiros, usando ou não um framework, você decide. Lembre-se, um desenvolvedor eficaz sabe o que construir e o que reutilizar.

Na entrevista de "code review", esteja preparado para responder algumas perguntas sobre essas bibliotecas e, caso utilize, sobre o framework. Como e por que você as escolheu e com quais outras alternativas você está familiarizado, serão algumas dessas perguntas.

Como este é um processo de "code review", evite adicionar código gerado ao projeto.

***Obs***: Para realizar esse teste, não crie um repositório público! Esse desafio é compartilhado apenas com pessoas que estamos entrevistando e gostaríamos que permanecesse assim.  


Nós utilizamos o [Docker](https://www.docker.com/products/docker) para executar a aplicação, por isso, pedimos que você faça o mesmo neste teste. Isso garante que tenhamos um resultado idêntico ao seu quando testarmos sua aplicação.

Para facilitar o teste, disponibilizamos alguns containters que vão lhe ajudar a construir e executar sua aplicação, mas fique à vontade para alterá-los conforme preferir!

Para executá-los é fácil, acesse o diretório `user-api` e execute o comando: `docker-compose -up -d`.

## Requisitos mínimos para o teste:

- Persistência de dados em banco não relacional. Pode ser MongoDB.
- Camada de cache em memória. Pode ser Redis, Memcached, ou APCU.
- Utilização de um ORM para manipulação dos dados.
- Testes unitários.
- Documentação de setup e do funcionamento das APIs (um Makefile cai muito bem!).
- Caso decida utilizar um framework, utilize um  micro-framework, você está construindo microsserviços!

## Instruções

- Clone este repositório.
- Crie uma nova branch chamada `dev`
- Desenvolva as aplicação.
- Crie uma "pull request" da branch `dev` para a "branch" `main`. Essa PR deve conter as instruções para executarmos a sua aplicação, as tecnologias que você decidiu usar, por que decidiu utilizá-las e também as decisões que você teve quanto ao design do seu código.


## Requisitos das aplicação:

Nós desejamos que você crie uma aplicação básica (microserviço) este deverá ser um cadastro de usuários, contendo os seguintes recursos:

- Listar, exibir, criar, alterar e excluir usuários  

Coleção de usuários `user` deverá conter os campos: id, name, cpf, email, phone_number, created_at, updated_at  

Lembre-se de fazer a comunicação necessária entre os serviços para garantir a consistência de dados.  

Essa aplicação também **DEVE** estar de acordo com os padrões REST e **DEVE** ser disponibilizada uma documentação contendo os endpoints e payloads utilizados nas requisições.


## Critérios de avaliação

Dê uma atenção especial aos seguintes aspectos:

- Você **DEVE** usar bibliotecas de terceiros, e pode escolher usar um framework, utilizar não vai ser uma penalidade, mas você vai precisar justificar a sua escolha.
- Sua aplicação **DEVE** executar em containers Docker.
- Sua aplicação **DEVE** retornar um JSON válido e **DEVE** conter os recursos citados anteriormente.
- Você **DEVE** escrever um código testável e demonstrar isso escrevendo testes unitários.
- Você **DEVE** prestar atenção nas melhores práticas para segurança de APIs.
- Você **DEVE** seguir as diretizes de estilo de código.
- Você **NÃO** precisa desenvolver um "frontend" (telas) para esse teste.

Pontos que consideramos um bônus:

- Fazer uso de uma criptografia reversível de dados sensíveis do usuário, como: email, cpf e telefone, antes de persisti-los no banco de dados
- Suas respostas durante o code review
- Sua descrição do que foi feito na sua "pull request"
- Setup da aplicação em apenas um comando ou um script que facilite esse setup
- Outros tipos de testes, como: testes funcionais e de integração
- Histórico do seus commits, com mensagens descritivas do que está sendo desenvolvido.

---

Boa sorte!
