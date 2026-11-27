
package br.edu.telas;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import br.edu.persistencia.UsuarioDAO;
import br.edu.modelo.Usuario;

public class TelaLoginTeste extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private UsuarioDAO dao;

    public TelaLoginTeste() {

        dao = new UsuarioDAO();

        setTitle("Sistema de Login");
        setSize(350, 270);
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
        gbc.gridx = 0;
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
        gbc.insets = new Insets(12, 0, 5, 0);
        painel.add(botaoEntrar, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 0);
        painel.add(botaoCadastrar, gbc);

        botaoEntrar.addActionListener(e -> realizarLogin());
        botaoCadastrar.addActionListener(e -> abrirCadastro());

        add(painel);
    }

    private void realizarLogin() {

        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe o nome de usuário."
            );
            return;
        }

        if (usuario.length() < 4 || usuario.length() > 6) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome de usuário deve ter entre 4 e 6 caracteres."
            );
            return;
        }

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe a senha."
            );
            return;
        }

        if (senha.length() != 8) {
            JOptionPane.showMessageDialog(
                    this,
                    "A senha deve ter exatamente 8 caracteres."
            );
            return;
        }

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

    private void abrirCadastro() {

        JTextField novoUsuario = new JTextField(15);
        JPasswordField novaSenha = new JPasswordField(15);
        JPasswordField confirmarSenha = new JPasswordField(15);

        JPanel painelCadastro = new JPanel(new GridLayout(3, 2, 8, 8));

        painelCadastro.add(new JLabel("Novo usuário:"));
        painelCadastro.add(novoUsuario);

        painelCadastro.add(new JLabel("Senha:"));
        painelCadastro.add(novaSenha);

        painelCadastro.add(new JLabel("Confirmar senha:"));
        painelCadastro.add(confirmarSenha);

        int opcao = JOptionPane.showConfirmDialog(
                this,
                painelCadastro,
                "Cadastrar novo usuário",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcao != JOptionPane.OK_OPTION) {
            return;
        }

        String usuario = novoUsuario.getText().trim();
        String senha = new String(novaSenha.getPassword());
        String confirmacao = new String(confirmarSenha.getPassword());

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe um nome de usuário."
            );
            return;
        }

        if (usuario.length() < 4 || usuario.length() > 6) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome de usuário deve ter entre 4 e 6 caracteres."
            );
            return;
        }

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe uma senha."
            );
            return;
        }

        if (senha.length() != 8) {
            JOptionPane.showMessageDialog(
                    this,
                    "A senha deve ter exatamente 8 caracteres."
            );
            return;
        }

        if (!senha.equals(confirmacao)) {
            JOptionPane.showMessageDialog(
                    this,
                    "As senhas não coincidem."
            );
            return;
        }

        try {

            Usuario novo = new Usuario(usuario, senha);

            if (dao.cadastrar(novo)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário cadastrado com sucesso!"
                );

                campoUsuario.setText(usuario);
                campoSenha.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível cadastrar. Verifique os dados ou se o usuário já existe."
                );
            }

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao cadastrar usuário: " + e.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLoginTeste().setVisible(true);
        });
    }
}
