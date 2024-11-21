# Desafio Técnico - Sistema de Votação em Assembleias

## Descrição do Projeto

Este projeto é uma API RESTful desenvolvida em **Java** com **Spring Boot** para gerenciar sessões de votação em assembleias no contexto de cooperativismo. Cada associado possui um voto, e as decisões são tomadas por votação em assembleias. A API permite criar pautas, abrir sessões de votação, registrar votos e consultar o resultado da votação. A aplicação foi projetada para ser executada na nuvem, utilizando AWS para hospedagem e banco de dados.

## Funcionalidades

- **Cadastrar Pauta**: Registra uma nova pauta de votação.
- **Abrir Sessão de Votação**: Inicia uma sessão de votação para uma pauta, com duração configurável (padrão de 1 minuto).
- **Registrar Voto**: Permite que associados votem em pautas, com restrição para um único voto por pauta.
- **Consultar Resultado da Votação**: Calcula e retorna o resultado da votação para uma pauta.

# Endpoints da API

### Endereço IPv4 público da aplicação na AWS: 44.223.74.200

## Endpoints Pauta

URL base: `/api/pautas`

### Criar Pauta

- **Endpoint**: `POST /api/pautas`
- **Descrição**: Creates a new pauta.
- **Corpo da Requisição**:
  ```json
  {
    "nome": "Nome da Pauta"
  }
  ```
- **Resposta**:
  ```json
  {
    "id": 1,
    "nome": "Nome da Pauta"
  }
  ```

### Buscar todas as Pautas

- **Endpoint**: `GET /api/pautas`
- **Descrição**: Recupera uma lista de todas as pautas.
- **Resposta**:
  ```json
  [
    {
      "id": 1,
      "nome": "Nome da Pauta"
    }
  ]
  ```

### Buscar Pauta pelo ID

- **Endpoint**: `GET /api/pautas/{id}`
- **Descrição**: Retorna a pauta pelo ID.
- **Resposta**:
  ```json
  {
    "id": 1,
    "nome": "Pauta Name"
  }
  ```

### Busca o resultado de uma votação pela pauta

- **Endpoint**: `GET /api/pautas/{id}/resultado`
- **Descrição**: Calcula e retorna o resultado de uma votação de uma pauta.
- **Resposta**:
  ```json
  {
    "pautaId": 1,
    "totalVotos": 10,
    "votosSim": 6,
    "votosNao": 4
  }
  ```

---

## Endpoints Voto

Base URL: `/api/votos`

### Registrar Voto

- **Endpoint**: `POST /api/votos/registrar`
- **Descrição**: Registra voto para a pauta.
- **PArâmetros da Requisição**:
  - `pautaId`: ID da pauta
  - `associadoId`: ID do associado
  - `cpf` (opcional): CPF do associado
  - `voto`: Valor booleano (`true` para "sim", `false` para "não")
- **Resposta**:
  ```json
  {
    "id": 1,
    "pautaId": 1,
    "associadoId": 123,
    "cpf": "12345678909",
    "voto": true
  }
  ```

---

## Endpoints Sessão Votação

Base URL: `/api/sessoes`

### Abrir uma sessão

- **Endpoint**: `POST /api/sessoes/abrir/{pautaId}`
- **Descrição**: Abre uma sessão com duração opcional e padrão de 1 (um) minuto
- **Parâmetos da requisição**:
  - `pautaId`: ID da pauta.
  - `duracao` (opcional): Duração em minutos.
- **Resposta**:
  ```json
  {
    "id": 1,
    "pautaId": 1,
    "dataInicio": "2024-11-07T12:00:00",
    "duracao": 30,
    "status": "ATIVA"
  }
  ```

### Buscar sessão pelo ID

- **Endpoint**: `GET /api/sessoes/{id}`
- **Descrição**: Retorna uma sessão pelo ID.
- **Resposta**:
  ```json
  {
    "id": 1,
    "pautaId": 1,
    "dataInicio": "2024-11-07T12:00:00",
    "duracao": 30,
    "status": "ATIVA"
  }
  ```

---


### Tarefas Bônus Implementadas

- **Integração com Sistemas Externos**: Integração com um serviço externo para verificar, através do CPF, se o associado está apto a votar.
- **Mensageria e Filas**: Envio do resultado da votação para uma fila SQS da AWS quando a sessão de votação é encerrada.

## Tecnologias Utilizadas

- **Java 17** com **Spring Boot**
- **Spring Data JPA** para manipulação de banco de dados
- **MySQL (Amazon RDS)** como banco de dados
- **AWS Elastic Beanstalk** para deploy da aplicação
- **AWS SQS** para mensageria
- **Maven** para gerenciamento de dependências


## Estrutura do Projeto

- `src/main/java` - Código fonte do projeto
  - `controller` - Controladores REST que expõem os endpoints da API
  - `service` - Lógica de negócios
  - `repository` - Interfaces para acesso ao banco de dados
  - `config` - Configurações da aplicação
  - `model` - Entidades JPA para o banco de dados
- `src/main/resources` - Arquivos de configuração
  - `application.properties` - Configurações da aplicação, como URL do banco de dados e variáveis de ambiente
- `src/test/java` - Código de teste do projeto
  - `controller` - Testes para os controladores REST
  - `service` - Testes para a lógica de negócios
  - `DesafioTecnicoNtApplicationTests` - Classe principal de testes para a aplicação
  - `TestSecurityConfig` - Configurações de segurança para testes
- `src/test/resources` - Arquivos de configuração para testes
  - `application-test.properties` - Configurações específicas para o ambiente de testes, como banco de dados H2 e parâmetros de logging

## Pré-requisitos

- **Java 17** ou superior
- **Maven** para compilar o projeto
- **AWS CLI** configurado (se for implantar na AWS)
- **Docker** (opcional, para testes locais com banco de dados MySQL)
