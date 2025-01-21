# LiterAlura 📚

## Descrição
LiterAlura é um catálogo de livros interativo que permite aos usuários explorar e gerenciar uma biblioteca de livros utilizando a API Gutendex.

## Funcionalidades
- Buscar livros por ID
- Listar todos os livros
- Listar todos os autores
- Filtrar autores vivos em um determinado ano
- Filtrar livros por idioma

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- WebClient
- PostgreSQL
- Lombok

## Configuração do Projeto

### Pré-requisitos
- Java 17
- Maven
- PostgreSQL

### Configuração do Banco de Dados
1. Crie um banco de dados chamado `literalura`
2. Configure as credenciais no arquivo `application.properties`

### Executando o Projeto
```bash
mvn clean install
mvn spring-boot:run
```

## Uso
Após iniciar a aplicação, um menu interativo será exibido no console com as seguintes opções:
1. Buscar livro por ID
2. Listar todos os livros
3. Listar todos os autores
4. Listar autores vivos em um determinado ano
5. Listar livros por idioma
0. Sair

## Autor
Eduardo Assunção
