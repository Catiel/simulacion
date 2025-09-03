import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SimulacionLanzamientos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulacionLanzamientos::mostrarVentana);
    }

    private static JTable simularMonedaTabla() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        String[] columnas = {"Corridas", "% Cara", "% Cruz"};
        Object[][] datos = new Object[corridas.length][3];
        for (int i = 0; i < corridas.length; i++) {
            int n = corridas[i];
            Random rand = new Random();
            int cara = 0, cruz = 0;
            for (int j = 0; j < n; j++) {
                if (rand.nextBoolean()) cara++;
                else cruz++;
            }
            datos[i][0] = n;
            datos[i][1] = String.format("%.2f", (cara * 100.0) / n) + "%";
            datos[i][2] = String.format("%.2f", (cruz * 100.0) / n) + "%";
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static JTable simularDadoTabla() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        String[] columnas = {"Corridas", "% 1", "% 2", "% 3", "% 4", "% 5", "% 6"};
        Object[][] datos = new Object[corridas.length][7];
        for (int i = 0; i < corridas.length; i++) {
            int n = corridas[i];
            Random rand = new Random();
            int[] resultados = new int[6];
            for (int j = 0; j < n; j++) {
                resultados[rand.nextInt(6)]++;
            }
            datos[i][0] = n;
            for (int k = 0; k < 6; k++) {
                datos[i][k + 1] = String.format("%.2f", (resultados[k] * 100.0) / n) + "%";
            }
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static JTable simularDosDadosTabla() {
        int[] corridas = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        String[] columnas = new String[12];
        columnas[0] = "Corridas";
        for (int i = 2; i <= 12; i++) {
            columnas[i - 1] = "% " + i;
        }
        Object[][] datos = new Object[corridas.length][12];
        for (int i = 0; i < corridas.length; i++) {
            int n = corridas[i];
            Random rand = new Random();
            int[] resultados = new int[11];
            for (int j = 0; j < n; j++) {
                int dado1 = rand.nextInt(6) + 1;
                int dado2 = rand.nextInt(6) + 1;
                resultados[dado1 + dado2 - 2]++;
            }
            datos[i][0] = n;
            for (int k = 0; k < 11; k++) {
                datos[i][k + 1] = String.format("%.2f", (resultados[k] * 100.0) / n) + "%";
            }
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static void mostrarVentana() {
        JFrame frame = new JFrame("Simulación de Lanzamientos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Moneda", new JScrollPane(simularMonedaTabla()));
        tabs.addTab("Dado", new JScrollPane(simularDadoTabla()));
        tabs.addTab("Dos Dados", new JScrollPane(simularDosDadosTabla()));
        frame.add(tabs);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
