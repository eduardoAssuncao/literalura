# LiterAlura

Uma aplicação Java para buscar e gerenciar informações sobre livros usando a API do Gutendex (Projeto Gutenberg).

## Funcionalidades

1. **Busca de Livros**
   - Buscar livros por título na API do Gutendex
   - Os livros encontrados são automaticamente salvos no banco de dados local

2. **Listagem de Livros**
   - Listar todos os livros salvos no banco de dados
   - Buscar livros por idioma específico
   - Contar quantidade de livros por idioma

3. **Gerenciamento de Autores**
   - Listar todos os autores cadastrados
   - Listar autores que estavam vivos em um ano específico
   - Visualizar informações detalhadas dos autores:
     - Nome
     - Ano de nascimento
     - Ano de falecimento (se aplicável)
     - Lista de livros do autor

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Project Lombok
- Maven

## Como Executar

1. Certifique-se de ter o Java 17 instalado
2. Clone o repositório
3. Execute o comando: `mvn spring-boot:run`
4. O programa apresentará um menu com as seguintes opções:
   ```
   1 - Buscar livro por título
   2 - Listar todos os livros
   3 - Buscar livros por idioma
   4 - Listar todos os autores
   5 - Listar autores vivos em um ano específico
   6 - Contar livros por idioma
   0 - Sair
   ```

## Estrutura do Projeto

- `model/`: Classes de domínio (Book, Author, etc.)
- `repository/`: Interfaces de repositório para acesso ao banco de dados
- `service/`: Serviços para consumo da API e conversão de dados
- `main/`: Classes principais da aplicação

## API Utilizada

O projeto utiliza a API do Gutendex (http://gutendex.com/), que é uma API RESTful para o Projeto Gutenberg. Ela fornece acesso a um vasto catálogo de livros em domínio público.

## Exemplos de Uso

1. **Buscar um Livro**
   - Selecione a opção 1
   - Digite o título do livro
   - O livro será buscado na API e salvo localmente

2. **Listar Livros por Idioma**
   - Selecione a opção 3
   - Digite o código do idioma (ex: pt, en, fr)
   - O sistema mostrará todos os livros naquele idioma

3. **Buscar Autores por Período**
   - Selecione a opção 5
   - Digite um ano
   - O sistema mostrará todos os autores que estavam vivos naquele ano

4. **Contar Livros por Idioma**
   - Selecione a opção 6
   - Digite o código do idioma
   - O sistema mostrará a quantidade total de livros naquele idioma

## Autor
Eduardo Assunção
