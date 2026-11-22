package br.edu.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashSenha {

    private static final int ITERACOES = 210000;
    private static final int TAMANHO_HASH = 256;
    private static final int TAMANHO_SALT = 16;

    public static String gerarSalt() {

        byte[] salt = new byte[TAMANHO_SALT];

        new SecureRandom().nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    public static String gerarHash(String senha, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        byte[] saltBytes = Base64.getDecoder().decode(salt);

        PBEKeySpec especificacao = new PBEKeySpec(
                senha.toCharArray(),
                saltBytes,
                ITERACOES,
                TAMANHO_HASH
        );

        try {
            SecretKeyFactory fabrica = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256"
            );

            byte[] hash = fabrica.generateSecret(especificacao).getEncoded();

            return Base64.getEncoder().encodeToString(hash);

        } finally {
            especificacao.clearPassword();
        }
    }

    public static boolean verificarSenha(
            String senha,
            String salt,
            String hashArmazenado)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        String hashCalculado = gerarHash(senha, salt);

        return MessageDigest.isEqual(
                Base64.getDecoder().decode(hashCalculado),
                Base64.getDecoder().decode(hashArmazenado)
        );
    }

}