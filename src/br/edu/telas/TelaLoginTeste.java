
package br.edu.telas;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import br.edu.persistencia.UsuarioDAO;

public class TelaLoginTeste extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private UsuarioDAO dao;

    public TelaLoginTeste() {

        dao = new UsuarioDAO();

        setTitle("Sistema de Login");
        setSize(320, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);

        JLabel titulo = new JLabel("Bem-vindo!");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painel.add(titulo, gbc);

        gbc.gridwidth = 1;

        JLabel labelUsuario = new JLabel("Usuário");
        gbc.gridy = 1;
        painel.add(labelUsuario, gbc);

        campoUsuario = new JTextField(15);
        gbc.gridx = 1;
        painel.add(campoUsuario, gbc);

        JLabel labelSenha = new JLabel("Senha");
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(labelSenha, gbc);

        campoSenha = new JPasswordField(15);
        gbc.gridx = 1;
        painel.add(campoSenha, gbc);

        JButton botaoEntrar = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 0, 0, 0);
        painel.add(botaoEntrar, gbc);

        botaoEntrar.addActionListener(e -> realizarLogin());

        add(painel);
    }

    private void realizarLogin() {

        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        try {

            if (dao.autenticar(usuario, senha)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login realizado com sucesso!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário ou senha incorretos."
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao realizar login: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLoginTeste().setVisible(true);
        });
    }
}
