package model;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class TaskWidget implements Renderizable, Interactivo {

    private int id;
    private String titulo;
    private int memoriaKB;
    private boolean activo;
    private ArrayList<String> tareas;
    private ArrayList<Boolean> estado;

    public TaskWidget(int id, String titulo, int memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.memoriaKB = memoriaKB;
        this.activo = true;
        this.tareas = new ArrayList<>();
        this.estado = new ArrayList<>();
    }

    public void agregarTarea(String tarea) {
        tareas.add(tarea);
        estado.add(false);
    }

    public void completarTarea(int posicion) {
        if (posicion >= 0 && posicion < tareas.size()) {
            estado.set(posicion, true);
        }
    }

    private int buscarPrimeraPendiente() {
        for (int i = 0; i < tareas.size(); i++) {
            if (!estado.get(i)) {
                return i;
            }
        }
        return -1;
    }

    private String getTextoTareas() {
        StringBuilder sb = new StringBuilder("<html>");
        for (int i = 0; i < tareas.size(); i++) {
            sb.append(estado.get(i) ? "[x] " : "[ ] ");
            sb.append(tareas.get(i)).append("<br>");
        }
        sb.append("</html>");
        return sb.toString();
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
        int pendientes = 0;
        for (Boolean hecha : estado) {
            if (!hecha) {
                pendientes++;
            }
        }
        return "Tareas: " + tareas.size() + " | Pendientes: " + pendientes;
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
        int pos = buscarPrimeraPendiente();
        if (pos == -1) {
            return "No hay tareas pendientes";
        }
        completarTarea(pos);
        return "Tarea completada: " + tareas.get(pos);
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JLabel lblTareas = new JLabel(getTextoTareas());
        JButton btnCompletar = new JButton("Completar siguiente");

        btnCompletar.addActionListener(e -> {
            interactuar();
            lblTareas.setText(getTextoTareas());
        });

        panel.add(lblTareas, BorderLayout.CENTER);
        panel.add(btnCompletar, BorderLayout.SOUTH);
        return panel;
    }
}
