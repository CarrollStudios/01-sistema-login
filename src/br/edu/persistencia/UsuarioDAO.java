
package br.edu.persistencia;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Collections;

import br.edu.modelo.Usuario;

public class UsuarioDAO {

    private final String arquivo = "usuarios.txt";

    public UsuarioDAO() {
        inicializarArquivo();
    }

    private void inicializarArquivo() {
        Path caminho = Paths.get(arquivo);

        try {
            if (Files.notExists(caminho)) {
                Files.write(
                    caminho,
                    Collections.singletonList("root;toor"),
                    StandardCharsets.UTF_8
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao inicializar os usuários.", e);
        }
    }

    public boolean cadastrar(Usuario usuario) throws IOException {

        String nome = usuario.getUsuario().trim();
        String senha = usuario.getSenha().trim();

        if (nome.isEmpty() || senha.isEmpty()) {
            return false;
        }

        if (usuarioExiste(nome)) {
            return false;
        }

        try (BufferedWriter escritor = Files.newBufferedWriter(
                Paths.get(arquivo),
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND)) {

            escritor.write(nome + ";" + senha);
            escritor.newLine();
        }

        return true;
    }

    public boolean autenticar(String nome, String senha) throws IOException {

        nome = nome.trim();
        senha = senha.trim();

        if (nome.isEmpty() || senha.isEmpty()) {
            return false;
        }

        try (BufferedReader leitor = Files.newBufferedReader(
                Paths.get(arquivo), StandardCharsets.UTF_8)) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";", 2);

                if (dados.length == 2
                        && dados[0].equals(nome)
                        && dados[1].equals(senha)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean usuarioExiste(String nome) throws IOException {

        try (BufferedReader leitor = Files.newBufferedReader(
                Paths.get(arquivo), StandardCharsets.UTF_8)) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";", 2);

                if (dados.length == 2 && dados[0].equals(nome)) {
                    return true;
                }
            }
        }

        return false;
    }
}
