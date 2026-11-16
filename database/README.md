# Banco de Dados — Sistema de Login

## Estrutura do banco de dados

O banco de dados utilizado pelo sistema se chama `sistema_login`.

### Tabela: usuarios

| Campo | Tipo | Descrição |
|---|---|---|
| id | INT | Identificador único e automático |
| nome | VARCHAR(100) | Nome do usuário |
| email | VARCHAR(150) | E-mail único do usuário |
| senha | VARCHAR(255) | Armazenamento do hash da senha |

## Testes CRUD

Foram realizados testes diretamente no MySQL para verificar as operações básicas de manipulação dos dados:

- **CREATE (INSERT):** inserção de um usuário de teste.
- **READ (SELECT):** consulta dos usuários cadastrados.
- **UPDATE:** atualização do nome do usuário de teste.
- **DELETE:** exclusão do usuário de teste.

Os testes foram concluídos com sucesso, confirmando o funcionamento das operações básicas na tabela `usuarios`.

## Organização dos arquivos

- `sql/01-criar-banco.sql`: criação do banco de dados e da tabela de usuários.
- `sql/02-testes-crud.sql`: comandos utilizados para os testes CRUD.

## Desenvolvimento

Responsável pela implementação e organização da parte de banco de dados: Hadassa Micaele.


