package com.paparazziteam.securityapplicationapp.framework.network

import com.paparazziteam.securityapplicationapp.data.remote.PokemonService
import com.paparazziteam.securityapplicationapp.data.remote.PokemonServiceImpl
import com.paparazziteam.securityapplicationapp.data.remote.PokemonServiceSsl
import com.paparazziteam.securityapplicationapp.data.remote.PokemonServiceSslImpl
import dagger.Binds

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add("pokeapi.co", "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc=")
            .build()
    }

    @HttpOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @SslOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClientSsl(
        certificatePinner: CertificatePinner
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
    }



    @HttpRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(
        @HttpOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SslRetrofit
    @Singleton
    @Provides
    fun provideRetrofitSsl(
        @SslOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModuleBinds {
    //Every time you request a PokemonService, you will get a new instance of PokemonServiceImpl

    @Singleton
    @Binds
    abstract fun providePokemonService(pokemonService: PokemonServiceImpl): PokemonService

    //Every time you request a PokemonService, you will get a new instance of PokemonServiceImpl
    @Singleton
    @Binds
    abstract fun providePokemonServiceSsl(pokemonService: PokemonServiceSslImpl): PokemonServiceSsl
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SslOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SslRetrofit