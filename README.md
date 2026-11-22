 # 🪐 Carroll Studios

**Projeto Final de Programação Orientada a Objetos • IFCE Maranguape • 2026.2 ✦**

<div align="center">

[![Frases sobre o sistema](https://readme-typing-svg.demolab.com?font=Poppins&weight=400&size=16&duration=2400&pause=1200&color=C4B5FD&center=true&vCenter=true&width=620&height=30&lines=Portal+de+entrada+da+constela%C3%A7%C3%A3o;Autentica%C3%A7%C3%A3o+e+acesso+estelar)](https://github.com/)

</div>

---

## 🪐 Sobre este repositório

O **sistema-login** é um dos três repositórios da organização **Carroll Studios**. Ele funciona como portal de entrada para os aplicativos desenvolvidos pela equipe, realizando a autenticação do usuário e permitindo o acesso à Tela de Seleção dos módulos disponíveis.

O projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos do IFCE Campus Maranguape.

## 🎯 Objetivos

- Autenticar o usuário por meio da validação de nome de usuário e senha.
- Permitir o cadastro de novos usuários.
- Direcionar o usuário, após o login válido, para a Tela de Seleção.
- Disponibilizar acesso aos módulos Agenda de Contatos e Projeto Livre.
- Permitir o retorno à Tela de Login por meio da opção Voltar.

## 💜 Tecnologias utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java_Swing-8B5CF6?style=for-the-badge&logo=java&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

- **Java:** linguagem de programação utilizada no desenvolvimento do sistema.
- **Java Swing:** biblioteca utilizada para a construção das interfaces gráficas.
- **Persistência em arquivo:** armazenamento local das informações dos usuários.
- **Git e GitHub:** ferramentas utilizadas para controle de versão e organização do desenvolvimento.

## 🚀 Funcionamento do sistema

O Sistema de Login funciona como ponto de entrada para os aplicativos da Carroll Studios.

Seu fluxo principal é composto pelas seguintes etapas:

1. O usuário acessa a Tela de Login.
2. Informa seu nome de usuário e senha.
3. O sistema verifica as credenciais cadastradas.
4. Caso a autenticação seja válida, o usuário é direcionado à Tela de Seleção.
5. Na Tela de Seleção, poderá acessar os módulos disponíveis ou retornar à tela inicial.

O sistema também prevê o cadastro de novos usuários, permitindo que suas informações sejam utilizadas posteriormente na autenticação.

## 🌌 Persistência local

O armazenamento dos dados dos usuários é realizado localmente por meio de um arquivo de texto chamado `usuarios.txt`.

A classe `UsuarioDAO` é responsável pelas operações relacionadas ao armazenamento e à consulta dos usuários, incluindo:

- Cadastro de novos usuários.
- Verificação da existência de um usuário.
- Autenticação por nome de usuário e senha.

A persistência é realizada em arquivo local, sem utilização de banco de dados MySQL.

O arquivo `usuarios.txt` é gerado durante a execução e não é enviado ao GitHub, pois está incluído nas regras do `.gitignore`.

## 📂 Organização do projeto

O repositório segue uma estrutura organizada para separar os arquivos da aplicação, documentação e recursos utilizados no desenvolvimento.

```text
01-sistema-login/
│
├── docs/
├── resources/
├── src/
│   └── br/
│       └── edu/
│           ├── modelo/
│           │   └── Usuario.java
│           │
│           └── persistencia/
│               ├── UsuarioDAO.java
│               └── TesteUsuarioDAO.java
│
├── support/
│
├── .gitignore
├── LICENSE
└── README.md
```

## 🛸 Como executar

Para executar o projeto em ambiente de desenvolvimento:

1. Instale e configure o JDK.
2. Abra o Eclipse IDE.
3. Importe o repositório como um projeto Java existente.
4. Execute a classe principal da aplicação para iniciar o sistema.

A classe principal deverá ser identificada na versão integrada da aplicação, juntamente com as telas gráficas.

## 👩‍🚀 Equipe

**Carroll Studios**

Projeto desenvolvido pelos estudantes do IFCE Campus Maranguape para a disciplina de Programação Orientada a Objetos.

## ✨ Controle de versão

O desenvolvimento do projeto é acompanhado por meio do Git e GitHub.

As funcionalidades são organizadas em branches, enquanto os commits registram as alterações realizadas durante cada etapa do desenvolvimento.

Essa organização permite acompanhar a evolução do sistema e facilitar a integração das funcionalidades desenvolvidas pela equipe.

---

<div align="center">

**✦ CARROLL STUDIOS ✦**

*Uma constelação de ideias, projetos e possibilidades.*

**IFCE Maranguape • 2026.2**

</div>
