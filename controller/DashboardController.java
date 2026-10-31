package controller;

import java.util.ArrayList;
import model.Dashboard;
import model.Renderizable;
import view.DashboardView;

public class DashboardController {

    private Dashboard modelo;
    private DashboardView vista;

    public DashboardController(Dashboard modelo, DashboardView vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.addListarListener(e -> listar());
        vista.addBuscarListener(e -> buscar());
        vista.addOrdenarListener(e -> ordenar());

        vista.mostrarWidgets(modelo.getWidgets());
    }

    private void listar() {
        ArrayList<Renderizable> activos = modelo.listarActivos();
        vista.mostrarWidgets(activos);
        vista.mostrarMensaje(construirLista("Widgets activos: " + activos.size(), activos));
    }

    private void buscar() {
        String texto = vista.getTextoBusqueda();
        Renderizable w = modelo.buscar(texto);
        if (w == null) {
            vista.mostrarMensaje("No se encontró ningún widget con: " + texto);
        } else {
            vista.mostrarMensaje("ID: " + w.getId() + "\n"
                    + "Título: " + w.getTitulo() + "\n"
                    + "Configuración: " + w.getConfiguracion() + "\n"
                    + "Memoria: " + w.getMemoriaKB() + " KB");
        }
    }

    private void ordenar() {
        modelo.ordenarPorMemoria();
        vista.mostrarWidgets(modelo.getWidgets());
        vista.mostrarMensaje(construirLista("Ordenados por memoria (menor a mayor):", modelo.getWidgets()));
    }

    private String construirLista(String encabezado, ArrayList<Renderizable> lista) {
        StringBuilder sb = new StringBuilder(encabezado + "\n");
        for (Renderizable w : lista) {
            sb.append(w.getId()).append(" - ").append(w.getTitulo())
              .append(" (").append(w.getMemoriaKB()).append(" KB)\n");
        }
        return sb.toString();
    }
}
