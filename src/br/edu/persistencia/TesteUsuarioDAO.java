package br.edu.persistencia;

import java.io.IOException;
import br.edu.modelo.Usuario;

public class TesteUsuarioDAO {

    public static void main(String[] args) {

        UsuarioDAO dao = new UsuarioDAO();

        try {

            System.out.println("Login inicial: "
                    + dao.autenticar("root", "toor"));

            System.out.println("Cadastro com usuario vazio: "
                    + dao.cadastrar(new Usuario("", "1234")));

            System.out.println("Cadastro com senha vazia: "
                    + dao.cadastrar(new Usuario("usuario1", "")));

            System.out.println("Cadastro com usuario e senha validos: "
                    + dao.cadastrar(new Usuario("usuario1", "1234")));

            System.out.println("Cadastro duplicado: "
                    + dao.cadastrar(new Usuario("usuario1", "1234")));

            System.out.println("Login correto: "
                    + dao.autenticar("usuario1", "1234"));

            System.out.println("Login com senha incorreta: "
                    + dao.autenticar("usuario1", "0000"));

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}