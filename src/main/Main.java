package main;

import view.TelaCadastro;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja executada na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true); // Torna a janela visível
        });
    }
}