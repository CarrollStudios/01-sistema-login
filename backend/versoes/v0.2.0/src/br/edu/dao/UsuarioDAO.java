package br.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.conexao.Conexao;
import br.edu.modelo.Usuario;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenha());

            int resultado = comando.executeUpdate();

            if (resultado > 0) {
                System.out.println("Usuário cadastrado com sucesso!");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar usuário: " + erro.getMessage());
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, email);

            try (ResultSet resultado = comando.executeQuery()) {

                if (resultado.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setId(resultado.getInt("id"));
                    usuario.setNome(resultado.getString("nome"));
                    usuario.setEmail(resultado.getString("email"));
                    usuario.setSenha(resultado.getString("senha"));

                    return usuario;
                }
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao buscar usuário: " + erro.getMessage());
        }

        return null;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getSenha());
            comando.setInt(4, usuario.getId());

            int resultado = comando.executeUpdate();

            if (resultado > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar usuário: " + erro.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            int resultado = comando.executeUpdate();

            if (resultado > 0) {
                System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao excluir usuário: " + erro.getMessage());
        }
    }
}