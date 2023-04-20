package com.darksoft.mihoroscopo.core

import com.darksoft.mihoroscopo.data.network.MiHoroscopoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Le decimos que puede acceder cualquier clase, etc. (Alcance)
object NetworkModule {

    @Provides
    @Singleton // Mostrar mensajes de log que contienen las peticiones y respuestas de la API
    fun provideOkInterceptor(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton // Unica instancia
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://aztro.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideMiHoroscopoApi(retrofit: Retrofit): MiHoroscopoApi {
        return retrofit.create(MiHoroscopoApi::class.java)
    }

}