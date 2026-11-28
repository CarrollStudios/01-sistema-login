package br.edu.telas;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

/**
 * Classe ajudante com a identidade visual da Carroll Studios.
 * Guarda as cores e as fontes e cria os componentes que se repetem
 * nas telas (botão roxo, link, rótulos e caixa de aviso).
 * Assim as telas de login, cadastro e seleção ficam com o mesmo visual
 * sem repetir código.
 */
public class Estilo {

    // ===== Paleta da Carroll Studios =====
    public static final Color FUNDO = new Color(0x0D0B1E);
    public static final Color FUNDO_SECUNDARIO = new Color(0x1A1730);
    public static final Color ROXO = new Color(0x8B5CF6);
    public static final Color ROXO_ESCURO = new Color(0x6D28D9);
    public static final Color TEXTO = new Color(0xE9E4FF);

    // Tons de apoio (variações da paleta)
    public static final Color TEXTO_SUAVE = new Color(0xA99FD6);
    public static final Color TEXTO_DICA = new Color(0x7D75AB);
    public static final Color BORDA = new Color(0x2E2858);
    public static final Color ROXO_CLARO = new Color(0xA78BFA);

    // ===== Fontes =====
    // Georgia (com serifa) para títulos e Segoe UI para os textos: as duas já vêm no Windows.
    public static Font fonteTitulo(int tamanho) {
        return new Font("Georgia", Font.PLAIN, tamanho);
    }

    public static Font fonteTexto(int tamanho) {
        return new Font("Segoe UI", Font.PLAIN, tamanho);
    }

    public static Font fonteNegrito(int tamanho) {
        return new Font("Segoe UI", Font.BOLD, tamanho);
    }

    // ===== Imagens =====
    // Carrega uma imagem da pasta resources (ex.: "/images/simbolo-carroll.png").
    public static ImageIcon imagem(String caminho, int largura) {
        URL url = Estilo.class.getResource(caminho);
        if (url == null) {
            System.out.println("Imagem não encontrada: " + caminho);
            return null;
        }
        Image original = new ImageIcon(url).getImage();
        int altura = original.getHeight(null) * largura / original.getWidth(null);
        return new ImageIcon(original.getScaledInstance(largura, altura, Image.SCALE_SMOOTH));
    }

    // ===== Componentes =====

    /** Título grande (ex.: "boas-vindas!"). */
    public static JLabel titulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(fonteTitulo(26));
        rotulo.setForeground(TEXTO);
        return rotulo;
    }

    /** Texto pequeno e suave (subtítulo, nome do campo ou dica). */
    public static JLabel texto(String texto, int tamanho, Color cor) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setFont(fonteTexto(tamanho));
        rotulo.setForeground(cor);
        return rotulo;
    }

    /** Cabeçalho com o símbolo da empresa e a palavra "carroll". */
    public static JLabel cabecalho() {
        JLabel cabecalho = new JLabel("carroll");
        cabecalho.setIcon(imagem("/images/simbolo-carroll.png", 58));
        cabecalho.setIconTextGap(10);
        cabecalho.setFont(new Font("Georgia", Font.BOLD, 30));
        cabecalho.setForeground(TEXTO);
        return cabecalho;
    }

    /** Botão roxo arredondado (Entrar, Cadastrar). Fica mais claro com o mouse em cima (hover). */
    public static JButton botaoRoxo(String texto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color cor = ROXO;
                if (getModel().isPressed()) {
                    cor = ROXO_ESCURO;
                } else if (getModel().isRollover()) {
                    cor = ROXO_CLARO;
                }
                g2.setColor(cor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        prepararBotao(botao);
        botao.setFont(fonteNegrito(16));
        botao.setForeground(Color.WHITE);
        return botao;
    }

    /** Link em texto (ex.: "não tem conta? cadastre-se"). A segunda parte fica em roxo. */
    public static JButton link(String parteNormal, String parteRoxa) {
        JButton link = new JButton("<html><span style='color:#A99FD6'>" + parteNormal
                + "</span> <b style='color:#A78BFA'>" + parteRoxa + "</b></html>");
        prepararBotao(link);
        link.setFont(fonteTexto(14));
        return link;
    }

    /** Caixa de aviso para os erros. Começa escondida. */
    public static JLabel caixaAviso() {
        JLabel aviso = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0x2A1838));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.setColor(ROXO_ESCURO);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        aviso.setFont(fonteTexto(13));
        aviso.setForeground(new Color(0xE9D5FF));
        aviso.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        aviso.setVisible(false);
        return aviso;
    }

    // Tira o visual padrão do Java dos botões e coloca a mãozinha do mouse.
    private static void prepararBotao(JButton botao) {
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.repaint();
            }
        });
    }
}
