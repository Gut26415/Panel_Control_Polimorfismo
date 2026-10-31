package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.Renderizable;

public class DashboardView extends JFrame {

    private JPanel panelWidgets;
    private JTextField txtBuscar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnOrdenar;
    private JTextArea areaInfo;

    public DashboardView() {
        super("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 700);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Dashboard Empresarial", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel barraBotones = new JPanel(new FlowLayout());
        txtBuscar = new JTextField(15);
        btnBuscar = new JButton("Buscar (ID o nombre)");
        btnListar = new JButton("Listar activos");
        btnOrdenar = new JButton("Ordenar por memoria");
        barraBotones.add(txtBuscar);
        barraBotones.add(btnBuscar);
        barraBotones.add(btnListar);
        barraBotones.add(btnOrdenar);

        JPanel norte = new JPanel(new BorderLayout());
        norte.add(lblTitulo, BorderLayout.NORTH);
        norte.add(barraBotones, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        // Cuadrícula de widgets
        panelWidgets = new JPanel(new GridLayout(0, 3, 10, 10));
        add(new JScrollPane(panelWidgets), BorderLayout.CENTER);

        areaInfo = new JTextArea(7, 40);
        areaInfo.setEditable(false);
        add(new JScrollPane(areaInfo), BorderLayout.SOUTH);
    }

    public void mostrarWidgets(ArrayList<Renderizable> lista) {
        panelWidgets.removeAll();
        for (Renderizable w : lista) {
            panelWidgets.add(w.obtenerVista());
        }
        panelWidgets.revalidate();
        panelWidgets.repaint();
    }

    public void mostrarMensaje(String texto) {
        areaInfo.setText(texto);
    }

    public String getTextoBusqueda() {
        return txtBuscar.getText().trim();
    }

    public void addListarListener(ActionListener l) {
        btnListar.addActionListener(l);
    }

    public void addBuscarListener(ActionListener l) {
        btnBuscar.addActionListener(l);
    }

    public void addOrdenarListener(ActionListener l) {
        btnOrdenar.addActionListener(l);
    }
}
