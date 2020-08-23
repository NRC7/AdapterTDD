package com.nrc7.adapter3.api;

import com.nrc7.adapter3.model.IndicadorEconomico;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("{tipoIndicador}")
    Call<IndicadorEconomico> getIndicadorEconomico(@Path("tipoIndicador") String tipo);
}
