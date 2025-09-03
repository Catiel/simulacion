import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimulacionLanzamientos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulacionLanzamientos::mostrarVentana);
    }

    private static void mostrarVentana() {
        JFrame frame = new JFrame("Simulación de Lanzamientos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Moneda", new JScrollPane(simularMonedaPanel()));
        tabs.addTab("Dado", new JScrollPane(simularDadoPanel()));
        tabs.addTab("Dos Dados", new JScrollPane(simularDosDadosPanel()));

        frame.add(tabs);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JTextArea simularMonedaPanel() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.append("Corridas | Cara  | Cruz\n");
        area.append("--------------------------\n");
        for (int n : corridas) {
            Random rand = new Random();
            int cara = 0, cruz = 0;
            for (int i = 0; i < n; i++) {
                if (rand.nextBoolean()) cara++;
                else cruz++;
            }
            area.append(String.format("%7d | %5d | %5d\n", n, cara, cruz));
        }
        area.setEditable(false);
        return area;
    }

    private static JTextArea simularDadoPanel() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.append("Corridas |  1   2   3   4   5   6\n");
        area.append("-----------------------------------\n");
        for (int n : corridas) {
            Random rand = new Random();
            int[] resultados = new int[6];
            for (int i = 0; i < n; i++) {
                resultados[rand.nextInt(6)]++;
            }
            area.append(String.format("%7d | %3d %3d %3d %3d %3d %3d\n", n, resultados[0], resultados[1], resultados[2], resultados[3], resultados[4], resultados[5]));
        }
        area.setEditable(false);
        return area;
    }

    private static JTextArea simularDosDadosPanel() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.append("Corridas |  2   3   4   5   6   7   8   9  10  11  12\n");
        area.append("--------------------------------------------------------\n");
        for (int n : corridas) {
            Random rand = new Random();
            int[] resultados = new int[11];
            for (int i = 0; i < n; i++) {
                int dado1 = rand.nextInt(6) + 1;
                int dado2 = rand.nextInt(6) + 1;
                resultados[dado1 + dado2 - 2]++;
            }
            area.append(String.format("%7d | %3d %3d %3d %3d %3d %3d %3d %3d %3d %3d %3d\n",
                n,
                resultados[0], resultados[1], resultados[2], resultados[3], resultados[4], resultados[5],
                resultados[6], resultados[7], resultados[8], resultados[9], resultados[10]
            ));
        }
        area.setEditable(false);
        return area;
    }
}

