package model;

import java.awt.BorderLayout;
import java.awt.Font;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ClockWidget implements Renderizable {

    private int id;
    private String titulo;
    private int memoriaKB;
    private boolean activo;
    private String formato;
    private JPanel vista;

    public ClockWidget(int id, String titulo, String formato, int memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.formato = formato;
        this.memoriaKB = memoriaKB;
        this.activo = true;
    }

    public String getHoraActual() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(formato));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getConfiguracion() {
        return "Formato: " + formato;
    }

    @Override
    public boolean getActivo() {
        return activo;
    }

    @Override
    public int getMemoriaKB() {
        return memoriaKB;
    }

    @Override
    public JComponent obtenerVista() {
        if (vista == null) {
            vista = new JPanel(new BorderLayout());
            vista.setBorder(BorderFactory.createTitledBorder(titulo));

            JLabel lblHora = new JLabel(getHoraActual(), SwingConstants.CENTER);
            lblHora.setFont(new Font("Arial", Font.BOLD, 28));
            vista.add(lblHora, BorderLayout.CENTER);

            Timer timer = new Timer(1000, e -> lblHora.setText(getHoraActual()));
            timer.start();
        }
        return vista;
    }
}
