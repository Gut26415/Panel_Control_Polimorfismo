package model;

import javax.swing.JComponent;

public interface Renderizable extends Comparable<Renderizable> {
    int getId();
    String getTitulo();
    String getConfiguracion();
    boolean getActivo();
    int getMemoriaKB();
    JComponent obtenerVista();

    @Override
    default int compareTo(Renderizable otro) {
        return Integer.compare(this.getMemoriaKB(), otro.getMemoriaKB());
    }
}
