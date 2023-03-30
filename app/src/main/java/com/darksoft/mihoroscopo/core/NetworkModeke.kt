package com.darksoft.mihoroscopo.core

import com.darksoft.mihoroscopo.data.network.MiHoroscopoApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Le decimos que puede acceder cualquier clase, etc. (Alcance)
object NetworkModeke {

    @Provides
    @Singleton // Unica instancia
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://aztro.sameerkumar.website")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMiHoroscopoApi(retrofit: Retrofit): MiHoroscopoApi {
        return retrofit.create(MiHoroscopoApi::class.java)
    }

}