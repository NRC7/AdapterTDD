package com.nrc7.adapter3.api;

import com.nrc7.adapter3.model.SerieIndicador;

import java.util.List;

// Comunicar el DataSource.class con las clases que implementan IData
public interface IData {

    void getIndicadorSerie(List<SerieIndicador> list);
}
