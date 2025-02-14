package com.paparazziteam.securityapplicationapp.data.remote

import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    //pokemon/ditto = ditto as a parameter
    @Headers("Content-Type: application/json")
    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): Response<PokemonResponse>

    @Headers("Content-Type: application/json")
    @GET("pokemon/{name}")
    suspend fun getPokemonHttp(
        @Path("name") name: String
    ): Response<PokemonResponse>
}


