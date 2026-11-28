package br.edu.telas;

import java.awt.*;
import java.awt.geom.Path2D;

import javax.swing.*;

/**
 * Tela de Seleção (front-end da Gih). Abre depois do login.
 *
 * Tem os botões que o guia do professor pede:
 * - Agenda de Contatos
 * - Projeto Livre (o jogo Professor Rogério Fora de Órbita)
 * - Voltar (volta para o login)
 *
 * POR ENQUANTO os cartões da agenda e do jogo só mostram um aviso:
 * os três projetos ainda vão ser juntados num programa só.
 */
public class TelaSelecao extends JFrame {

    private static final String ICONE_AGENDA = "agenda";
    private static final String ICONE_FOGUETE = "foguete";

    public TelaSelecao(String usuario) {
        setTitle("Carroll Studios · Seleção");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icone = Estilo.imagem("/images/simbolo-carroll.png", 64);
        if (icone != null) {
            setIconImage(icone.getImage());
        }

        FundoEstrelado fundo = new FundoEstrelado();
        montarTela(fundo, usuario);
        setContentPane(fundo);
        pack();
        setLocationRelativeTo(null);
    }

    private void montarTela(FundoEstrelado fundo, String usuario) {
        int x = 48;
        int largura = 384;

        JLabel cabecalho = Estilo.cabecalho();
        cabecalho.setBounds(x - 6, 26, 300, 64);
        fundo.add(cabecalho);

        // Mostra o nome de quem entrou (ex.: "olá, gih26!") com a estrelinha desenhada do lado.
        JLabel titulo = Estilo.titulo("olá, " + usuario.toLowerCase() + "!");
        titulo.setIcon(Estilo.estrela(16, Estilo.TEXTO));
        titulo.setHorizontalTextPosition(SwingConstants.LEFT); // texto à esquerda, estrela à direita
        titulo.setIconTextGap(10);
        titulo.setBounds(x, 104, largura, 36);
        fundo.add(titulo);

        JLabel subtitulo = Estilo.texto("para onde vamos hoje?", 14, Estilo.TEXTO_SUAVE);
        subtitulo.setBounds(x, 140, largura, 20);
        fundo.add(subtitulo);

        // ----- Cartão da Agenda -----
        JButton cartaoAgenda = criarCartao("Agenda de Contatos",
                "adicionar, procurar, alterar", "e excluir contatos", ICONE_AGENDA);
        cartaoAgenda.setBounds(x, 188, largura, 96);
        cartaoAgenda.addActionListener(e -> abrirAgenda());
        fundo.add(cartaoAgenda);

        // ----- Cartão do Projeto Livre -----
        JButton cartaoJogo = criarCartao("Projeto Livre",
                "jogo Professor Rogério", "Fora de Órbita", ICONE_FOGUETE);
        cartaoJogo.setBounds(x, 300, largura, 96);
        cartaoJogo.addActionListener(e -> abrirJogo());
        fundo.add(cartaoJogo);

        // ----- Botão Voltar -----
        JButton botaoVoltar = criarBotaoVoltar();
        botaoVoltar.setBounds(x, 540, largura, 46);
        botaoVoltar.addActionListener(e -> voltarParaLogin());
        fundo.add(botaoVoltar);
    }

    // ===== Ações dos botões =====

    private void abrirAgenda() {
        // Depois (quando os projetos forem juntados): abrir a tela inicial da Agenda e dispose();
        JOptionPane.showMessageDialog(this,
                "A Agenda de Contatos abre aqui quando os projetos forem juntados.");
    }

    private void abrirJogo() {
        // Depois (quando os projetos forem juntados): abrir o jogo (new TelaJogo()) e dispose();
        JOptionPane.showMessageDialog(this,
                "O jogo abre aqui quando os projetos forem juntados.");
    }

    private void voltarParaLogin() {
        new TelaLogin().setVisible(true);
        dispose();
    }

    // ===== Desenho dos componentes =====

    // Cartão clicável: bolinha roxa com anel de órbita, título e descrição.
    private JButton criarCartao(String titulo, String linha1, String linha2, String icone) {
        JButton cartao = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                boolean mouseEmCima = getModel().isRollover();

                // Fundo e borda (a borda fica roxa com o mouse em cima)
                g2.setColor(Estilo.FUNDO_SECUNDARIO);
                g2.fillRoundRect(0, 0, w - 1, h - 1, 32, 32);
                g2.setColor(mouseEmCima ? Estilo.ROXO : Estilo.BORDA);
                g2.setStroke(new BasicStroke(mouseEmCima ? 1.6f : 1f));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 32, 32);

                // Bolinha roxa (o "planeta")
                int cx = 48;
                int cy = h / 2;
                g2.setPaint(new GradientPaint(cx - 29, cy - 29, Estilo.ROXO, cx + 29, cy + 29, Estilo.ROXO_ESCURO));
                g2.fillOval(cx - 29, cy - 29, 58, 58);

                // Anel de órbita (só a parte da frente, inclinada)
                Graphics2D anel = (Graphics2D) g2.create();
                anel.rotate(Math.toRadians(-18), cx, cy);
                anel.setColor(Estilo.ROXO);
                anel.setStroke(new BasicStroke(2f));
                anel.drawArc(cx - 40, cy - 10, 80, 20, 180, 180);
                anel.dispose();

                // Ícone branco dentro da bolinha
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                if (ICONE_AGENDA.equals(icone)) {
                    g2.drawRoundRect(cx - 9, cy - 12, 18, 24, 4, 4);
                    g2.drawOval(cx - 3, cy - 6, 6, 6);
                    g2.drawArc(cx - 6, cy + 2, 12, 10, 0, 180);
                } else {
                    Path2D foguete = new Path2D.Double();
                    foguete.moveTo(cx, cy - 13);
                    foguete.curveTo(cx + 7, cy - 7, cx + 6, cy + 2, cx + 5, cy + 6);
                    foguete.lineTo(cx - 5, cy + 6);
                    foguete.curveTo(cx - 6, cy + 2, cx - 7, cy - 7, cx, cy - 13);
                    g2.draw(foguete);
                    g2.drawOval(cx - 2, cy - 5, 4, 4);
                    g2.drawLine(cx - 5, cy + 2, cx - 9, cy + 8);
                    g2.drawLine(cx + 5, cy + 2, cx + 9, cy + 8);
                    g2.drawLine(cx, cy + 9, cx, cy + 12);
                }

                // Textos
                int tx = 112;
                g2.setColor(Estilo.TEXTO);
                g2.setFont(Estilo.fonteNegrito(17));
                g2.drawString(titulo, tx, cy - 8);
                g2.setColor(Estilo.TEXTO_SUAVE);
                g2.setFont(Estilo.fonteTexto(13));
                g2.drawString(linha1, tx, cy + 12);
                g2.drawString(linha2, tx, cy + 29);

                // Setinha
                g2.setColor(Estilo.ROXO);
                g2.setFont(Estilo.fonteTexto(26));
                g2.drawString("›", w - 34, cy + 9);
                g2.dispose();
            }
        };
        prepararBotao(cartao);
        return cartao;
    }

    // Botão Voltar: só com contorno roxo.
    private JButton criarBotaoVoltar() {
        JButton botao = new JButton("‹  Voltar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? Estilo.FUNDO_SECUNDARIO : Estilo.FUNDO);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);
                g2.setColor(new Color(0x4C3F8F));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 24, 24);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        prepararBotao(botao);
        botao.setFont(Estilo.fonteTexto(15));
        botao.setForeground(new Color(0xC9C1F2));
        return botao;
    }

    private void prepararBotao(JButton botao) {
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setRolloverEnabled(true);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Para testar só esta tela: botão direito no arquivo → Run As → Java Application.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaSelecao("gih26").setVisible(true));
    }
}
