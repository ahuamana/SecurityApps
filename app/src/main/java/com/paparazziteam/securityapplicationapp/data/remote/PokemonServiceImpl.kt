package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import com.paparazziteam.securityapplicationapp.framework.network.HttpsRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PokemonServiceImpl @Inject constructor(
   @HttpsRetrofit private val retrofit: Retrofit
) : PokemonService {
    override suspend fun getPokemon(name: String): Response<PokemonResponse> {
        return retrofit.create(PokemonService::class.java).getPokemon(name)
    }

    override suspend fun getPokemonHttp(name: String): Response<PokemonResponse> {
        return retrofit.create(PokemonService::class.java).getPokemonHttp(name)
    }
}