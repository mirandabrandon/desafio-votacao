# Desafio Técnico NT - Sistema de Votação em Assembleias

## Descrição do Projeto

Este projeto é uma API RESTful desenvolvida em **Java** com **Spring Boot** para gerenciar sessões de votação em assembleias no contexto de cooperativismo. Cada associado possui um voto, e as decisões são tomadas por votação em assembleias. A API permite criar pautas, abrir sessões de votação, registrar votos e consultar o resultado da votação. A aplicação foi projetada para ser executada na nuvem, utilizando AWS para hospedagem e banco de dados.

## Funcionalidades

- **Cadastrar Pauta**: Registra uma nova pauta de votação.
- **Abrir Sessão de Votação**: Inicia uma sessão de votação para uma pauta, com duração configurável (padrão de 1 minuto).
- **Registrar Voto**: Permite que associados votem em pautas, com restrição para um único voto por pauta.
- **Consultar Resultado da Votação**: Calcula e retorna o resultado da votação para uma pauta.

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

## Pré-requisitos

- **Java 17** ou superior
- **Maven** para compilar o projeto
- **AWS CLI** configurado (se for implantar na AWS)
- **Docker** (opcional, para testes locais com banco de dados MySQL)

## Configuração

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/desafio-tecnico-nt.git
   cd desafio-tecnico-nt
