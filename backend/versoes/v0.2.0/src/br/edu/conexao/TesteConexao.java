package br.edu.conexao;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        Connection conexao = Conexao.conectar();

        if (conexao != null) {
            System.out.println("Conexão com MySQL realizada com sucesso!");

            try {
                conexao.close();
            } catch (Exception erro) {
                System.out.println("Erro ao fechar conexão: " + erro.getMessage());
            }

        } else {
            System.out.println("Não foi possível conectar ao MySQL.");
        }
    }
}