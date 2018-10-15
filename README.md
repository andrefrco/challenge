# Desafio Desenvolvedor Backend - ContaAzul

O objetivo do desafio é construir uma API REST para geração de boletos que será consumido por um módulo de um sistema de gestão financeira de microempresas.
No final do desafio vamos ter os seguintes endpoints para:
- Criar boleto
- Listar boletos
- Ver detalhes
- Pagar um boleto
- Cancelar um boleto


## Desenvolvimento
- Java 8
- Maven
- H2
- Spring Boot

## Execução
### Local

- Clonar esse [repositório](https://github.com/andrefrco/challenge)
- Posicionar no diretório de origem e executar o seguinte comando `mvn spring-boot:run` então estará disponível na porta 8080.

### Heroku

- A aplicação também se encontra disponível no Heroku no seguinte link: https://challengecontaazul.herokuapp.com/

## Funcionalidades

- Para efetuar o teste basta adicionar os endpoints listados abaixo em http://localhost:8080/ caso tenha sido feita a execução local. Ou na URL disponível no Heroku: https://challengecontaazul.herokuapp.com/

### Criar Boleto

Endpoint: rest/bankslips
Método: POST

```json
{
  "due_date":"2018-01-01",
  "total_in_cents":"100000",
  "customer":"Trillian Company",
  "status":"PENDING"
}
```

### Lista de boletos

Endpoint: rest/bankslips/
Método: GET

### Detalhe do boleto

Endpoint: rest/bankslips/{id}
Método: GET

### Pagar boleto

Endpoint: rest/bankslips/{id}/payments
Método: POST

```json
{
  "payment_date":"2018-06-30"
}
```

### Cancelar boleto

Endpoint: rest/bankslips/{id}
Método: DELETE
