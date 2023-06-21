package com.paparazziteam.securityapplicationapp.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paparazziteam.securityapplicationapp.usecases.GetPokemonUseCase
import com.paparazziteam.securityapplicationapp.usecases.PokemonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _statePokemon:MutableStateFlow<PokemonState> = MutableStateFlow(PokemonState.Idle)
    val statePokemon: StateFlow<PokemonState> = _statePokemon


    fun getPokemonInfo(name:String) = viewModelScope.launch() {
        getPokemonUseCase
            .invoke(name)
            .onStart {
                _statePokemon.value = PokemonState.ShowLoading
            }.onEach {
                withContext(Dispatchers.Main){
                    _statePokemon.value = PokemonState.Success(it)
                }
            }.catch {
                withContext(Dispatchers.Main){
                    _statePokemon.value = PokemonState.Error(it)
                }
            }.launchIn(viewModelScope)
    }
}