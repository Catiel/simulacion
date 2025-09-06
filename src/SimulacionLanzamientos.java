import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.function.IntSupplier;

public class SimulacionLanzamientos {
    private static final Long SEMILLA = null;
    private static final Random rand = (SEMILLA == null) ? new Random() : new Random(SEMILLA);
    private static final int[] CORRIDAS = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulacionLanzamientos::mostrarVentana);
    }

    private static JTable simularMonedaTabla() {
        String[] columnas = {"Corridas", "% Cara", "% Cruz"};
        Object[][] datos = new Object[CORRIDAS.length][3];
        for (int i = 0; i < CORRIDAS.length; i++) {
            int n = CORRIDAS[i];
            IntSupplier roll = () -> rand.nextBoolean() ? 0 : 1;
            int[] resultados = simulateCounts(n, 2, roll);
            Object[] fila = buildRow(n, resultados);
            datos[i] = fila;
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static JTable simularDadoTabla() {
        String[] columnas = {"Corridas", "% 1", "% 2", "% 3", "% 4", "% 5", "% 6"};
        Object[][] datos = new Object[CORRIDAS.length][7];
        for (int i = 0; i < CORRIDAS.length; i++) {
            int n = CORRIDAS[i];
            IntSupplier roll = () -> rand.nextInt(6);
            int[] resultados = simulateCounts(n, 6, roll);
            Object[] fila = buildRow(n, resultados);
            datos[i] = fila;
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static JTable simularDosDadosTabla() {
        String[] columnas = new String[12];
        columnas[0] = "Corridas";
        for (int i = 2; i <= 12; i++) {
            columnas[i - 1] = "% " + i;
        }
        Object[][] datos = new Object[CORRIDAS.length][12];
        for (int i = 0; i < CORRIDAS.length; i++) {
            int n = CORRIDAS[i];
            IntSupplier roll = () -> (rand.nextInt(6) + 1) + (rand.nextInt(6) + 1) - 2;
            int[] resultados = simulateCounts(n, 11, roll);
            Object[] fila = buildRow(n, resultados);
            datos[i] = fila;
        }
        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        return tabla;
    }

    private static String formatPercent(int count, int total) {
        return String.format("%.2f", (count * 100.0) / total) + "%";
    }

    private static int[] simulateCounts(int n, int categories, IntSupplier roll) {
        int[] resultados = new int[categories];
        for (int i = 0; i < n; i++) {
            int r = roll.getAsInt();
            if (r >= 0 && r < categories) resultados[r]++;
        }
        return resultados;
    }

    private static Object[] buildRow(int n, int[] counts) {
        Object[] row = new Object[counts.length + 1];
        row[0] = n;
        for (int k = 0; k < counts.length; k++) {
            row[k + 1] = formatPercent(counts[k], n);
        }
        return row;
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
