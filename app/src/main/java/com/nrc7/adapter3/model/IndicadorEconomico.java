package com.nrc7.adapter3.model;

import java.util.List;

public class IndicadorEconomico {

    private String nombre, unidadMedida;
    private List<SerieIndicador> serie;

    public String getNombre() {
        return nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public List<SerieIndicador> getSerie() {
        return serie;
    }
}
