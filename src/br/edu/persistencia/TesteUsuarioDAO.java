package br.edu.persistencia;

import java.io.IOException;
import br.edu.modelo.Usuario;

public class TesteUsuarioDAO {

    public static void main(String[] args) {

        UsuarioDAO dao = new UsuarioDAO();

        try {
            System.out.println("Login inicial: "
                    + dao.autenticar("root", "toor"));

            Usuario novoUsuario = new Usuario("teste", "1234");

            System.out.println("Cadastro: "
                    + dao.cadastrar(novoUsuario));

            System.out.println("Login novo usuário: "
                    + dao.autenticar("teste", "1234"));

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}