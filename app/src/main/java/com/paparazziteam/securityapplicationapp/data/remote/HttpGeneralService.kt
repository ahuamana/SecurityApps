package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface HttpGeneralService {
    @Headers("Content-Type: application/json")
    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Response<PokemonResponse>
}