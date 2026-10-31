import controller.DashboardController;
import javax.swing.SwingUtilities;
import model.Dashboard;
import view.DashboardView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard modelo = new Dashboard();
            modelo.cargarInicial();

            DashboardView vista = new DashboardView();
            new DashboardController(modelo, vista);

            vista.setVisible(true);
        });
    }
}
