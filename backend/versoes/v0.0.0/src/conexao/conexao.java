package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() {

        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("Conexao com o banco realizada com sucesso!");

            return conexao;

        } catch (SQLException erro) {

            System.out.println("Erro ao conectar com o banco: " + erro.getMessage());

            return null;
        }
    }
}