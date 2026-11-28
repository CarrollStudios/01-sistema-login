package br.edu.telas;

import javax.swing.*;

/**
 * Tela de Login do Sistema de Login (front-end da Gih).
 *
 * O que ela faz:
 * 1. Mostra os campos de usuário e senha.
 * 2. Confere se os campos seguem as regras combinadas com a Hadassa
 *    (usuário de 4 a 6 caracteres, senha com exatamente 8, nenhum campo vazio).
 * 3. Se estiver tudo certo, pergunta ao back-end se o login é válido.
 *
 * POR ENQUANTO a tela só confere as regras.
 * Quando as classes da Hadassa chegarem na main, a conferência passa a ser:
 *     dao.autenticar(usuario, senha)   (da classe UsuarioDAO)
 */
public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JLabel aviso;

    public TelaLogin() {
        setTitle("Carroll Studios · Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icone = Estilo.imagem("/images/simbolo-carroll.png", 64);
        if (icone != null) {
            setIconImage(icone.getImage()); // ícone da janela
        }

        FundoEstrelado fundo = new FundoEstrelado();
        montarTela(fundo);
        setContentPane(fundo);
        pack();                          // ajusta a janela ao tamanho do fundo (480 x 640)
        setLocationRelativeTo(null);     // abre no meio da tela
    }

    // Coloca cada componente no lugar, como no protótipo.
    private void montarTela(FundoEstrelado fundo) {
        int x = 48;          // margem da esquerda
        int largura = 384;   // largura dos campos e do botão

        JLabel cabecalho = Estilo.cabecalho();
        cabecalho.setBounds(x - 6, 26, 300, 64);
        fundo.add(cabecalho);

        JLabel titulo = Estilo.titulo("boas-vindas!");
        titulo.setBounds(x, 104, largura, 36);
        fundo.add(titulo);

        JLabel subtitulo = Estilo.texto("entre para acessar a agenda e o jogo", 14, Estilo.TEXTO_SUAVE);
        subtitulo.setBounds(x, 140, largura, 20);
        fundo.add(subtitulo);

        // ----- Usuário -----
        JLabel rotuloUsuario = Estilo.texto("usuário", 13, new java.awt.Color(0xC9C1F2));
        rotuloUsuario.setBounds(x, 176, largura, 18);
        fundo.add(rotuloUsuario);

        campoUsuario = CampoArredondado.texto("seu usuário", CampoArredondado.ICONE_PESSOA);
        campoUsuario.setBounds(x, 198, largura, 46);
        fundo.add(campoUsuario);

        JLabel dicaUsuario = Estilo.texto("de 4 a 6 caracteres", 12, Estilo.TEXTO_DICA);
        dicaUsuario.setBounds(x, 248, largura, 16);
        fundo.add(dicaUsuario);

        // ----- Senha -----
        JLabel rotuloSenha = Estilo.texto("senha", 13, new java.awt.Color(0xC9C1F2));
        rotuloSenha.setBounds(x, 282, largura, 18);
        fundo.add(rotuloSenha);

        campoSenha = CampoArredondado.senha("sua senha");
        campoSenha.setBounds(x, 304, largura, 46);
        fundo.add(campoSenha);

        JLabel dicaSenha = Estilo.texto("exatamente 8 caracteres", 12, Estilo.TEXTO_DICA);
        dicaSenha.setBounds(x, 354, largura, 16);
        fundo.add(dicaSenha);

        // ----- Aviso de erro (começa escondido) -----
        aviso = Estilo.caixaAviso();
        aviso.setBounds(x, 384, largura, 38);
        fundo.add(aviso);

        // ----- Botão Entrar -----
        JButton botaoEntrar = Estilo.botaoRoxo("Entrar");
        botaoEntrar.setBounds(x, 438, largura, 48);
        botaoEntrar.addActionListener(e -> entrar());
        fundo.add(botaoEntrar);
        getRootPane().setDefaultButton(botaoEntrar); // apertar Enter também entra

        // ----- Link para o cadastro -----
        JButton linkCadastro = Estilo.link("não tem conta?", "cadastre-se");
        linkCadastro.setBounds(x, 500, largura, 30);
        linkCadastro.addActionListener(e -> abrirCadastro());
        fundo.add(linkCadastro);
    }

    // Chamado quando a pessoa clica em Entrar (ou aperta Enter).
    private void entrar() {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        // Regras combinadas com a Hadassa (a tela avisa o motivo do erro).
        if (usuario.isEmpty() || senha.isEmpty()) {
            mostrarAviso("preencha o usuário e a senha.");
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

        aviso.setVisible(false);

        // ===== Ligação com o back-end =====
        // As classes da Hadassa ainda não estão na main.
        // Quando chegarem, este trecho vira:
        //
        // try {
        //     if (dao.autenticar(usuario, senha)) {
        //         new TelaSelecao(usuario).setVisible(true);
        //         dispose();
        //     } else {
        //         mostrarAviso("usuário ou senha incorretos.");
        //         campoSenha.setText("");
        //     }
        // } catch (IOException e) {
        //     mostrarAviso("erro ao ler os usuários.");
        // }
        JOptionPane.showMessageDialog(this,
                "Os dados seguem as regras! A conferência no usuarios.txt\n"
                + "entra quando as classes da Hadassa chegarem na main.");
    }

    // Chamado quando a pessoa clica em "cadastre-se".
    private void abrirCadastro() {
        // Depois: new TelaCadastro().setVisible(true); e dispose();
        JOptionPane.showMessageDialog(this, "A Tela de Cadastro vem em um próximo passo.");
    }

    // Mostra a caixa roxa com a mensagem de erro.
    private void mostrarAviso(String mensagem) {
        aviso.setText("✦  " + mensagem);
        aviso.setVisible(true);
    }

    // Para testar só esta tela: botão direito no arquivo → Run As → Java Application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}
