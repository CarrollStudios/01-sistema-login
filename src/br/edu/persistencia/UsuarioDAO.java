
package br.edu.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
                Files.createFile(caminho);
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao inicializar os usuários.", e
            );
        }
    }

    public boolean cadastrar(Usuario usuario) throws IOException {

        String nome = usuario.getUsuario().trim();
        String senha = usuario.getSenha();

        if (nome.length() < 4 || nome.length() > 6
                || senha.trim().isEmpty()
                || senha.length() != 8
                || nome.contains(";")
                || nome.contains("\n")
                || nome.contains("\r")
                || senha.contains(";")
                || senha.contains("\n")
                || senha.contains("\r")) {

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

        } catch (NoSuchAlgorithmException
                | InvalidKeySpecException e) {

            throw new IOException(
                    "Erro ao proteger a senha.", e
            );
        }
    }

    public boolean autenticar(String nome, String senha)
            throws IOException {

        nome = nome.trim();

        if (nome.length() < 4 || nome.length() > 6
                || senha.isEmpty()
                || senha.length() != 8) {

            return false;
        }

        List<String> linhas = Files.readAllLines(
                Paths.get(arquivo),
                StandardCharsets.UTF_8
        );

        for (String linha : linhas) {

            String[] dados = linha.split(";", 3);

            if (dados.length == 3 && dados[0].equals(nome)) {

                try {

                    return HashSenha.verificarSenha(
                            senha,
                            dados[1],
                            dados[2]
                    );

                } catch (NoSuchAlgorithmException
                        | InvalidKeySpecException e) {

                    throw new IOException(
                            "Erro ao verificar a senha.", e
                    );
                }
            }
        }

        return false;
    }

    private boolean usuarioExiste(String nome) throws IOException {

        try (BufferedReader leitor = Files.newBufferedReader(
                Paths.get(arquivo),
                StandardCharsets.UTF_8)) {

            String linha;

            while ((linha = leitor.readLine()) != null) {

                String[] dados = linha.split(";", 3);

                if (dados.length == 3 && dados[0].equals(nome)) {
                    return true;
                }
            }
        }

        return false;
    }
}
