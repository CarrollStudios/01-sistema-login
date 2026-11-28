package br.edu.telas;

import java.awt.*;
import java.util.Random;

import javax.swing.JPanel;

/**
 * O fundo de todas as telas do sistema de login:
 * cor escura da Carroll Studios, céu de pontinhos discretos
 * e a assinatura "✦ CARROLL STUDIOS ✦" lá embaixo.
 *
 * Usa layout null: cada componente é colocado com setBounds(x, y, largura, altura),
 * igual a posicionar as coisas no protótipo.
 */
public class FundoEstrelado extends JPanel {

    public static final int LARGURA = 480;
    public static final int ALTURA = 640;

    private final int[][] estrelas = new int[45][3]; // x, y e brilho de cada pontinho

    public FundoEstrelado() {
        setLayout(null);
        setPreferredSize(new Dimension(LARGURA, ALTURA));
        setBackground(Estilo.FUNDO);

        // A "semente" 7 faz os pontinhos ficarem sempre no mesmo lugar.
        Random sorteio = new Random(7);
        for (int[] estrela : estrelas) {
            estrela[0] = sorteio.nextInt(LARGURA);
            estrela[1] = sorteio.nextInt(ALTURA - 60);
            estrela[2] = 70 + sorteio.nextInt(150);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // pinta o fundo escuro
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int[] estrela : estrelas) {
            g2.setColor(new Color(111, 103, 156, estrela[2]));
            g2.fillOval(estrela[0], estrela[1], 2, 2);
        }

        g2.setFont(Estilo.fonteTexto(11));
        g2.setColor(new Color(0x4F4880));
        String assinatura = "C A R R O L L   S T U D I O S";
        int largura = g2.getFontMetrics().stringWidth(assinatura);
        int x = (getWidth() - largura) / 2;
        int y = getHeight() - 18;
        g2.drawString(assinatura, x, y);
        // Estrelinhas dos dois lados (desenhadas, para não virar quadrado no Windows)
        Color corAssinatura = new Color(0x4F4880);
        Estilo.desenharEstrela(g2, x - 12, y - 4, 4, corAssinatura);
        Estilo.desenharEstrela(g2, x + largura + 12, y - 4, 4, corAssinatura);
        g2.dispose();
    }
}
