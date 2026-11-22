
package br.edu.persistencia;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.List;

import br.edu.modelo.Usuario;
import br.edu.seguranca.HashSenha;

public class UsuarioDAO {

    private final String arquivo = "usuarios.txt";

    public UsuarioDAO() {
        inicializarArquivo();
    }

    private void inicializarArquivo() {
        Path caminho = Paths.get(arquivo);

        try {
            if (Files.notExists(caminho)) {
                String salt = HashSenha.gerarSalt();
                String hash = HashSenha.gerarHash("toor", salt);

                Files.write(
                    caminho,
                    Collections.singletonList("root;" + salt + ";" + hash),
                    StandardCharsets.UTF_8
                );
            }
        } catch (IOException | NoSuchAlgorithmException
                | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao inicializar os usuários.", e);
        }
    }

    public boolean cadastrar(Usuario usuario) throws IOException {

        String nome = usuario.getUsuario().trim();
        String senha = usuario.getSenha().trim();

        if (nome.isEmpty() || senha.isEmpty()
                || nome.contains(";") || senha.contains(";")) {
            return false;
        }

        if (usuarioExiste(nome)) {
            return false;
        }

        try {
            String salt = HashSenha.gerarSalt();
            String hash = HashSenha.gerarHash(senha, salt);

            try (BufferedWriter escritor = Files.newBufferedWriter(
                    Paths.get(arquivo),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND)) {

                escritor.write(nome + ";" + salt + ";" + hash);
                escritor.newLine();
            }

            return true;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IOException("Erro ao proteger a senha.", e);
        }
    }

    public boolean autenticar(String nome, String senha) throws IOException {

        nome = nome.trim();
        senha = senha.trim();

        if (nome.isEmpty() || senha.isEmpty()) {
            return false;
        }

        List<String> linhas = Files.readAllLines(
                Paths.get(arquivo), StandardCharsets.UTF_8);

        for (int i = 0; i < linhas.size(); i++) {

            String[] dados = linhas.get(i).split(";", 3);

            if (dados.length == 2 && dados[0].equals(nome)) {

                if (dados[1].equals(senha)) {

                    try {
                        String salt = HashSenha.gerarSalt();
                        String hash = HashSenha.gerarHash(senha, salt);

                        linhas.set(i, nome + ";" + salt + ";" + hash);

                        Files.write(
                            Paths.get(arquivo),
                            linhas,
                            StandardCharsets.UTF_8
                        );

                        return true;

                    } catch (NoSuchAlgorithmException
                            | InvalidKeySpecException e) {
                        throw new IOException("Erro ao atualizar a senha.", e);
                    }
                }

                return false;
            }

            if (dados.length == 3 && dados[0].equals(nome)) {

                try {
                    return HashSenha.verificarSenha(
                            senha, dados[1], dados[2]);

                } catch (NoSuchAlgorithmException
                        | InvalidKeySpecException e) {
                    throw new IOException("Erro ao verificar a senha.", e);
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

                String[] dados = linha.split(";", 3);

                if (dados.length >= 2 && dados[0].equals(nome)) {
                    return true;
                }
            }
        }

        return false;
    }
}
