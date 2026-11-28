package br.edu.telas;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Campos de digitação com o visual da Carroll Studios:
 * caixa arredondada, ícone roxo à esquerda, texto de exemplo (placeholder)
 * e borda roxa quando o campo está selecionado (com foco).
 *
 * São dois tipos: texto normal (usuário) e senha (mostra ••••).
 */
public class CampoArredondado {

    public static final String ICONE_PESSOA = "pessoa";
    public static final String ICONE_CADEADO = "cadeado";

    /** Campo de texto normal (ex.: usuário). */
    public static JTextField texto(String placeholder, String icone) {
        JTextField campo = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                pintarCaixa(g, this, icone);
                super.paintComponent(g);
                pintarPlaceholder(g, this, placeholder, getText().isEmpty());
            }
        };
        preparar(campo);
        return campo;
    }

    /** Campo de senha (o que é digitado aparece como bolinhas). */
    public static JPasswordField senha(String placeholder) {
        JPasswordField campo = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                pintarCaixa(g, this, ICONE_CADEADO);
                super.paintComponent(g);
                pintarPlaceholder(g, this, placeholder, getPassword().length == 0);
            }
        };
        campo.setEchoChar('•');
        preparar(campo);
        return campo;
    }

    // Configurações iguais para os dois tipos de campo.
    private static void preparar(JTextComponent campo) {
        campo.setOpaque(false);
        campo.setFont(Estilo.fonteTexto(15));
        campo.setForeground(Estilo.TEXTO);
        campo.setCaretColor(Estilo.ROXO_CLARO);      // cor do tracinho que pisca
        campo.setSelectionColor(Estilo.ROXO_ESCURO);
        campo.setBorder(BorderFactory.createEmptyBorder(0, 42, 0, 14)); // espaço para o ícone
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                campo.repaint();
            }
        });
    }

    // Desenha a caixa arredondada e o ícone.
    private static void pintarCaixa(Graphics g, JComponent campo, String icone) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = campo.getWidth();
        int h = campo.getHeight();

        g2.setColor(Estilo.FUNDO_SECUNDARIO);
        g2.fillRoundRect(0, 0, w - 1, h - 1, 24, 24);
        g2.setColor(campo.hasFocus() ? Estilo.ROXO : Estilo.BORDA);
        g2.setStroke(new BasicStroke(campo.hasFocus() ? 1.6f : 1f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 24, 24);

        // Ícone
        g2.setColor(Estilo.ROXO);
        g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int cx = 22;
        int cy = h / 2;
        if (ICONE_PESSOA.equals(icone)) {
            g2.draw(new Ellipse2D.Double(cx - 4, cy - 8, 8, 8));       // cabeça
            Path2D corpo = new Path2D.Double();
            corpo.moveTo(cx - 7, cy + 8);
            corpo.curveTo(cx - 6, cy + 1, cx + 6, cy + 1, cx + 7, cy + 8);
            g2.draw(corpo);
        } else {
            g2.drawRoundRect(cx - 7, cy - 2, 14, 10, 3, 3);            // corpo do cadeado
            g2.drawArc(cx - 4, cy - 9, 8, 12, 0, 180);                 // alça
        }
        g2.dispose();
    }

    // Desenha o texto de exemplo quando o campo está vazio.
    private static void pintarPlaceholder(Graphics g, JComponent campo, String placeholder, boolean vazio) {
        if (!vazio || placeholder == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(Estilo.fonteTexto(15));
        g2.setColor(new Color(0x6F679C));
        FontMetrics fm = g2.getFontMetrics();
        int y = (campo.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(placeholder, 42, y);
        g2.dispose();
    }
}
