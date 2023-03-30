package com.darksoft.mihoroscopo.data.network

import com.darksoft.mihoroscopo.data.network.model.HoroscopoResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/* Aqui se haran todas nuestras llamadas, POST, GET, UPDATE, DELETE
* URL: https://aztro.sameerkumar.website?sign= <sign> &day= <day>*/

interface MiHoroscopoApi {

    @POST(".")
    suspend fun getHoroscopo(
        // llamarse igual al url
        @Query("sign") sign: String,
        @Query("day") day: String
    ): Response<HoroscopoResponse>


}