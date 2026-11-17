# Backend — Sistema de Login

## Sobre o projeto

Esta pasta reúne a parte de desenvolvimento Backend do Sistema de Login, implementada em Java, responsável pela comunicação entre a aplicação e o banco de dados MySQL.

O desenvolvimento está sendo organizado por versões, permitindo acompanhar a evolução da estrutura e das funcionalidades do projeto.

## Tecnologias utilizadas

- **Java:** linguagem utilizada no desenvolvimento.
- **MySQL:** sistema de gerenciamento do banco de dados.
- **JDBC:** tecnologia utilizada para a comunicação entre Java e MySQL.
- **MySQL Connector/J:** biblioteca responsável por permitir a conexão do Java com o MySQL.

## Estrutura de pastas

```text
backend/
├── lib/
│   └── mysql-connector-j-26.10.0.jar
├── versoes/
│   ├── v0.0.0/
│   ├── v0.1.0/
│   └── v0.2.0/
└── README.md
```

## Histórico de versões

| Versão | Descrição |
|---|---|
| v0.0.0 | Configuração inicial do projeto e inclusão da biblioteca MySQL Connector/J. |
| v0.1.0 | Evolução da estrutura de conexão entre Java e MySQL. |
| v0.2.0 | Implementação e teste da conexão entre Java e MySQL. |

## Biblioteca

O projeto utiliza a biblioteca MySQL Connector/J, armazenada na pasta `lib`.

Essa biblioteca permite que a aplicação Java estabeleça comunicação com o banco de dados MySQL.

## Integração com o Banco de Dados

A integração utiliza o banco de dados `sistema_login`, que possui a tabela `usuarios`.

A estrutura do banco de dados está documentada na pasta `database` do repositório principal.

## Desenvolvimento

Projeto acadêmico desenvolvido como parte do Sistema de Login, com evolução organizada por versões e integração entre as partes de Backend e Banco de Dados.
