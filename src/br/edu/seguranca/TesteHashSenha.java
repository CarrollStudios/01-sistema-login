package br.edu.seguranca;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TesteHashSenha {

    public static void main(String[] args) {

        try {

            String senha = "123456";

            String salt = HashSenha.gerarSalt();

            String hash = HashSenha.gerarHash(senha, salt);

            System.out.println("Senha original: " + senha);
            System.out.println("Salt: " + salt);
            System.out.println("Hash: " + hash);

            System.out.println("Verificacao da senha correta: "
                    + HashSenha.verificarSenha("123456", salt, hash));

            System.out.println("Verificacao da senha incorreta: "
                    + HashSenha.verificarSenha("000000", salt, hash));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

            System.out.println("Erro ao testar hash: " + e.getMessage());

        }
    }
}