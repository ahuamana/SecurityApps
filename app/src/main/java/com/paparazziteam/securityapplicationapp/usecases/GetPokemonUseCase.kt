package com.paparazziteam.securityapplicationapp.usecases

import com.paparazziteam.securityapplicationapp.data.repository.PokemonRepository
import com.paparazziteam.securityapplicationapp.domain.pokemon.PokemonResponse
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(name:String) = pokemonRepository.getPokemonInfo(name)
}


sealed class PokemonState {
    object Idle : PokemonState()
    object HideLoading : PokemonState()
    object ShowLoading : PokemonState()
    data class Success(val data: PokemonResponse) : PokemonState()
    data class Error(val error: Throwable) : PokemonState()
}