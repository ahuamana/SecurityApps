package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import com.paparazziteam.securityapplicationapp.framework.network.HttpRetrofit
import com.paparazziteam.securityapplicationapp.framework.network.HttpsRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class HttpGeneralServiceImpl @Inject constructor(
    @HttpRetrofit private val retrofit: Retrofit
) : HttpGeneralService {
    override suspend fun getPokemon(name: String): Response<PokemonResponse> {
        return retrofit.create(HttpGeneralService::class.java).getPokemon(name)
    }
}