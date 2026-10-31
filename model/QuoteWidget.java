package model;

import javax.swing.*;
import java.awt.BorderLayout;

public class QuoteWidget implements Renderizable {

    private int id;
    private String titulo;
    private int memoriaKB;
    private boolean activo;
    private String cita;
    private String autor;

    public QuoteWidget(int id, String titulo, String cita, String autor, int memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.cita = cita;
        this.autor = autor;
        this.memoriaKB = memoriaKB;
        this.activo = true;
    }

    public String getCita() {
        return cita;
    }

    public String getAutor() {
        return autor;
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
        return "Autor: " + autor;
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JLabel lblCita = new JLabel("<html><center>“" + cita + "”<br>- " + autor + "</center></html>",
                SwingConstants.CENTER);

        panel.add(lblCita, BorderLayout.CENTER);
        return panel;
    }
}
