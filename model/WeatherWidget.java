package model;

import java.awt.BorderLayout;
import javax.swing.*;

public class WeatherWidget implements Renderizable, Interactivo {

    private int id;
    private String titulo;
    private int memoriaKB;
    private boolean activo;
    private String ciudad;
    private double tempActual;
    private boolean fahrenheit;

    public WeatherWidget(int id, String titulo, String ciudad, double tempActual, int memoriaKB) {
        this.id = id;
        this.titulo = titulo;
        this.ciudad = ciudad;
        this.tempActual = tempActual;
        this.memoriaKB = memoriaKB;
        this.activo = true;
        this.fahrenheit = false;
    }

    public String getCiudad() {
        return ciudad;
    }

    public double getTempActual() {
        return tempActual;
    }

    public boolean isFahrenheit() {
        return fahrenheit;
    }

    public String getTemperaturaTexto() {
        if (fahrenheit) {
            double f = tempActual * 9 / 5 + 32;
            return String.format("%.1f °F", f);
        }
        return String.format("%.1f °C", tempActual);
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
        return "Ciudad: " + ciudad + " | Temperatura: " + getTemperaturaTexto();
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
        fahrenheit = !fahrenheit;
        return "Unidad cambiada: " + getTemperaturaTexto();
    }

    @Override
    public JComponent obtenerVista() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        JLabel lblTemp = new JLabel(ciudad + ": " + getTemperaturaTexto(), SwingConstants.CENTER);
        JButton btnUnidad = new JButton("Cambiar °C/°F");

        btnUnidad.addActionListener(e -> {
            interactuar();
            lblTemp.setText(ciudad + ": " + getTemperaturaTexto());
        });

        panel.add(lblTemp, BorderLayout.CENTER);
        panel.add(btnUnidad, BorderLayout.SOUTH);
        return panel;
    }
}
