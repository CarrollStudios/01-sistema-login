package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class conexao {

    public static Connection conectar() {
        Properties propriedades = new Properties();

        try (InputStream arquivo = conexao.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (arquivo == null) {
                throw new IOException("Arquivo config.properties não encontrado.");
            }

            propriedades.load(arquivo);

            String url = propriedades.getProperty("db.url");
            String usuario = propriedades.getProperty("db.user");
            String senha = propriedades.getProperty("db.password");

            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            return conexao;

        } catch (IOException | SQLException erro) {
            System.out.println("Erro ao conectar com o banco: " + erro.getMessage());
            return null;
        }
    }
}