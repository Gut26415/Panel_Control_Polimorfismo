package model;

import java.util.ArrayList;
import java.util.Collections;

public class Dashboard {

    private ArrayList<Renderizable> widgets;

    public Dashboard() {
        widgets = new ArrayList<>();
    }

    public void cargarInicial() {
        widgets.add(new WeatherWidget(1, "Clima Guatemala", "Guatemala", 24.5, 120));
        widgets.add(new WeatherWidget(2, "Clima Antigua", "Antigua", 22.0, 115));
        widgets.add(new WeatherWidget(3, "Clima Xela", "Quetzaltenango", 17.0, 118));

        TaskWidget tareasDia = new TaskWidget(4, "Tareas del Día", 200);
        tareasDia.agregarTarea("Revisar correos");
        tareasDia.agregarTarea("Reunión de equipo");
        tareasDia.agregarTarea("Entregar reporte");
        widgets.add(tareasDia);

        TaskWidget tareasProyecto = new TaskWidget(5, "Pendientes Proyecto", 180);
        tareasProyecto.agregarTarea("Escribir pruebas");
        tareasProyecto.agregarTarea("Corregir bug del login");
        widgets.add(tareasProyecto);

        widgets.add(new AlertWidget(6, "Alerta Servidor", "Servidor caído", "ALTA", 90));
        widgets.add(new AlertWidget(7, "Alerta Respaldo", "Respaldo pendiente", "MEDIA", 85));

        widgets.add(new ClockWidget(8, "Reloj Digital", "HH:mm:ss", 60));

        widgets.add(new QuoteWidget(9, "Frase del Día",
                "El único modo de hacer un gran trabajo es amar lo que haces", "Steve Jobs", 45));
        widgets.add(new QuoteWidget(10, "Frase Motivacional",
                "La disciplina vence al talento cuando el talento no se disciplina", "Anónimo", 50));
    }

    public void agregarWidget(Renderizable w) {
        widgets.add(w);
    }

    public ArrayList<Renderizable> getWidgets() {
        return widgets;
    }

    public ArrayList<Renderizable> listarActivos() {
        ArrayList<Renderizable> activos = new ArrayList<>();
        for (Renderizable w : widgets) {
            if (w.getActivo()) {
                activos.add(w);
            }
        }
        return activos;
    }


    public Renderizable buscar(String texto) {
        for (Renderizable w : widgets) {
            if (String.valueOf(w.getId()).equals(texto) || w.getTitulo().equalsIgnoreCase(texto)) {
                return w;
            }
        }
        return null;
    }

    public String interactuarConWidget(int id) {
        Renderizable w = buscar(String.valueOf(id));
        if (w == null) {
            return "No existe un widget con ID " + id;
        }
        if (w instanceof Interactivo) {
            Interactivo i = (Interactivo) w;
            return i.interactuar();
        }
        return "El widget " + w.getTitulo() + " no es interactivo";
    }


    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }
}
