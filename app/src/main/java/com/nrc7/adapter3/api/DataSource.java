package com.nrc7.adapter3.api;

import android.util.Log;

import com.nrc7.adapter3.model.Book;
import com.nrc7.adapter3.model.IndicadorEconomico;
import com.nrc7.adapter3.model.SerieIndicador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Fuente de datos de prueba (Api, DB)
public class DataSource {

    private static final String TAG = "Indicador";
    // Interface IData para pasar el String valor
    // Esta vez vamos a pasar una lista;
    IData callback;

    public DataSource(IData callback) {
        this.callback = callback;
    }

    // "uf" "dolar" "etc"
    public void getIndicadorList(String tipo) {

        // Instanciar la Api class
        Api api = RetrofitClient.getRetrofitInstance().create(Api.class);

        // Hacer llamado
        Call<IndicadorEconomico> call = api.getIndicadorEconomico("dolar");

        // Encolar llamado por el segundo plano
        call.enqueue(new Callback<IndicadorEconomico>() {
            @Override
            public void onResponse(Call<IndicadorEconomico> call, Response<IndicadorEconomico> response) {

                // Obtiene la respuesta
                List<SerieIndicador> list;
                if (response.body().getSerie() != null) {
                    list = response.body().getSerie();
                    // Interface envia la lista
                    callback.getIndicadorSerie(list);
                } else {
                    list = Arrays.asList(new SerieIndicador());
                }
            }

            @Override
            public void onFailure(Call<IndicadorEconomico> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }




    // Devuelve una lista de Book estatica para pruebas
    public List<Book> getBooks() {
        // List<> o Map<> = response.body();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Book book = new Book();
            book.setName("Libro " + i);
            book.setAuthor("Author " + i);
            books.add(book);
        }
        return books;
    }
}
