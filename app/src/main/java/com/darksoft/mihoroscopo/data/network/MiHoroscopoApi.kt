package com.darksoft.mihoroscopo.data.network

import com.darksoft.mihoroscopo.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/* Aqui se haran todas nuestras llamadas, POST, GET, UPDATE, DELETE
* URL: https://aztro.sameerkumar.website?sign= <sign> &day= <day>*/

interface MiHoroscopoApi {

    @GET("/{sign}/")
    suspend fun getHoroscope(
        // llamarse igual al url
        @Path("sign") sign: String,
        @Query("date") date: String,
        @Query("lang") lang: String

    ): Response<HoroscopeResponse>

}