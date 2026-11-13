# Banco de Dados — Sistema de Login

## Sobre o projeto

Este diretório contém os arquivos relacionados ao desenvolvimento do banco de dados do Sistema de Login, desenvolvido em equipe no projeto Carroll Studios.

## Tecnologias utilizadas

- MySQL 8.0
- SQL
- Git e GitHub

## Estrutura do banco de dados

O banco de dados utilizado pelo sistema se chama `sistema_login`.

### Tabela: usuarios

| Campo | Tipo | Descrição |
|---|---|---|
| id | INT | Identificador único e automático |
| nome | VARCHAR(100) | Nome do usuário |
| email | VARCHAR(150) | E-mail único do usuário |
| senha | VARCHAR(255) | Armazenamento do hash da senha |

## Organização dos arquivos

- `sql/01-criar-banco.sql`: criação do banco de dados e da tabela de usuários.

## Desenvolvimento

Responsável pela implementação e organização da parte de banco de dados: Hadassa Micaele.
