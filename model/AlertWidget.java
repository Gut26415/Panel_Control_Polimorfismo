package model;

import javax.swing.*;
import java.awt.BorderLayout;

public class AlertWidget implements Renderizable, Interactivo {

    private int id;
    private String titulo;
    private int memoriaKB;
    private boolean activo;
    private String mensaje;
    private String prioridad;
    private boolean leida;

    public AlertWidget(int id, String titulo, String mensaje, String prioridad, int memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.prioridad = prioridad;
        this.memoriaKB = memoriaKB;
        this.activo = true;
        this.leida = false;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public boolean isLeida() {
        return leida;
    }

    private String getTextoAlerta() {
        String texto = "<html>[" + prioridad + "] " + mensaje;
        if (leida) {
            texto += "<br>(leída)";
        }
        return texto + "</html>";
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
        return "Prioridad: " + prioridad + " | Leída: " + (leida ? "Sí" : "No");
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
    public String interactuar() {
        leida = true;
        return "Alerta marcada como leída";
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JLabel lblAlerta = new JLabel(getTextoAlerta(), SwingConstants.CENTER);
        JButton btnLeida = new JButton("Marcar como leída");

        btnLeida.addActionListener(e -> {
            interactuar();
            lblAlerta.setText(getTextoAlerta());
        });

        panel.add(lblAlerta, BorderLayout.CENTER);
        panel.add(btnLeida, BorderLayout.SOUTH);
        return panel;
    }
}
