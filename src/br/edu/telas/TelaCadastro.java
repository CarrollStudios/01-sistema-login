package br.edu.telas;

import java.awt.Color;

import javax.swing.*;

/**
 * Tela de Cadastro do Sistema de Login (front-end da Gih).
 *
 * O que ela faz:
 * 1. Mostra os campos de usuário, senha e confirmar senha.
 * 2. Confere as regras combinadas com a Hadassa:
 *    nenhum campo vazio, usuário de 4 a 6 caracteres, senha com exatamente 8
 *    e a confirmação igual à senha (essa última é só da tela: a UsuarioDAO não confere).
 * 3. Se estiver tudo certo, pede ao back-end para cadastrar.
 *
 * POR ENQUANTO a tela só confere as regras.
 * Quando as classes da Hadassa chegarem na main, o cadastro passa a ser:
 *     dao.cadastrar(new Usuario(usuario, senha))   (da classe UsuarioDAO)
 */
public class TelaCadastro extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmar;
    private JLabel aviso;

    public TelaCadastro() {
        setTitle("Carroll Studios · Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icone = Estilo.imagem("/images/simbolo-carroll.png", 64);
        if (icone != null) {
            setIconImage(icone.getImage());
        }

        FundoEstrelado fundo = new FundoEstrelado();
        montarTela(fundo);
        setContentPane(fundo);
        pack();
        setLocationRelativeTo(null);
    }

    private void montarTela(FundoEstrelado fundo) {
        int x = 48;
        int largura = 384;
        Color corRotulo = new Color(0xC9C1F2);

        JLabel cabecalho = Estilo.cabecalho();
        cabecalho.setBounds(x - 6, 26, 300, 64);
        fundo.add(cabecalho);

        JLabel titulo = Estilo.titulo("criar conta");
        titulo.setBounds(x, 104, largura, 36);
        fundo.add(titulo);

        JLabel subtitulo = Estilo.texto("preencha todos os campos", 14, Estilo.TEXTO_SUAVE);
        subtitulo.setBounds(x, 140, largura, 20);
        fundo.add(subtitulo);

        // ----- Usuário -----
        JLabel rotuloUsuario = Estilo.texto("usuário", 13, corRotulo);
        rotuloUsuario.setBounds(x, 172, largura, 18);
        fundo.add(rotuloUsuario);

        campoUsuario = CampoArredondado.texto("de 4 a 6 caracteres", CampoArredondado.ICONE_PESSOA);
        campoUsuario.setBounds(x, 194, largura, 46);
        fundo.add(campoUsuario);

        // ----- Senha -----
        JLabel rotuloSenha = Estilo.texto("senha", 13, corRotulo);
        rotuloSenha.setBounds(x, 252, largura, 18);
        fundo.add(rotuloSenha);

        campoSenha = CampoArredondado.senha("exatamente 8 caracteres");
        campoSenha.setBounds(x, 274, largura, 46);
        fundo.add(campoSenha);

        // ----- Confirmar senha -----
        JLabel rotuloConfirmar = Estilo.texto("confirmar senha", 13, corRotulo);
        rotuloConfirmar.setBounds(x, 332, largura, 18);
        fundo.add(rotuloConfirmar);

        campoConfirmar = CampoArredondado.senha("digite a senha de novo");
        campoConfirmar.setBounds(x, 354, largura, 46);
        fundo.add(campoConfirmar);

        // ----- Aviso de erro (começa escondido) -----
        aviso = Estilo.caixaAviso();
        aviso.setBounds(x, 414, largura, 38);
        fundo.add(aviso);

        // ----- Botão Cadastrar -----
        JButton botaoCadastrar = Estilo.botaoRoxo("Cadastrar");
        botaoCadastrar.setBounds(x, 466, largura, 48);
        botaoCadastrar.addActionListener(e -> cadastrar());
        fundo.add(botaoCadastrar);
        getRootPane().setDefaultButton(botaoCadastrar); // Enter também cadastra

        // ----- Link para voltar ao login -----
        JButton linkLogin = Estilo.link("já tem conta?", "voltar para o login");
        linkLogin.setBounds(x, 528, largura, 30);
        linkLogin.addActionListener(e -> voltarParaLogin());
        fundo.add(linkLogin);
    }

    // Chamado quando a pessoa clica em Cadastrar (ou aperta Enter).
    private void cadastrar() {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());
        String confirmacao = new String(campoConfirmar.getPassword());

        // Regras combinadas com a Hadassa (a tela avisa o motivo do erro).
        if (usuario.isEmpty() || senha.isEmpty() || confirmacao.isEmpty()) {
            mostrarAviso("preencha todos os campos.");
            return;
        }
        if (usuario.length() < 4 || usuario.length() > 6) {
            mostrarAviso("o usuário deve ter entre 4 e 6 caracteres.");
            return;
        }
        if (senha.length() != 8) {
            mostrarAviso("a senha deve ter exatamente 8 caracteres.");
            return;
        }
        if (!senha.equals(confirmacao)) {
            mostrarAviso("as senhas não são iguais.");
            campoConfirmar.setText("");
            return;
        }

        aviso.setVisible(false);

        // ===== Ligação com o back-end =====
        // Quando as classes da Hadassa chegarem, este trecho vira:
        //
        // try {
        //     if (dao.cadastrar(new Usuario(usuario, senha))) {
        //         JOptionPane.showMessageDialog(this, "Conta criada! Agora é só entrar.");
        //         voltarParaLogin();
        //     } else {
        //         mostrarAviso("esse usuário já existe.");
        //     }
        // } catch (IOException e) {
        //     mostrarAviso("erro ao salvar o usuário.");
        // }
        JOptionPane.showMessageDialog(this,
                "Os dados seguem as regras! O cadastro no usuarios.txt\n"
                + "entra quando as classes da Hadassa chegarem na main.");
        voltarParaLogin();
    }

    // Fecha esta tela e abre a de login.
    private void voltarParaLogin() {
        new TelaLogin().setVisible(true);
        dispose();
    }

    private void mostrarAviso(String mensagem) {
        aviso.setText(mensagem);
        aviso.setVisible(true);
    }

    // Para testar só esta tela: botão direito no arquivo → Run As → Java Application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCadastro().setVisible(true));
    }
}
