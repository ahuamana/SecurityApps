package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import com.paparazziteam.securityapplicationapp.framework.network.SslRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PokemonServiceSslImpl @Inject constructor(
    @SslRetrofit private val retrofit: Retrofit
) :PokemonServiceSsl {
    override suspend fun getPokemon(name: String): Response<PokemonResponse> {
        return  retrofit.create(PokemonServiceSsl::class.java).getPokemon(name)
    }
}