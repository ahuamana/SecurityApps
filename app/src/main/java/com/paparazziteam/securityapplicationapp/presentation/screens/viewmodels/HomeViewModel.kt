package com.paparazziteam.securityapplicationapp.presentation.screens.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paparazziteam.securityapplicationapp.R
import com.paparazziteam.securityapplicationapp.common.openLink
import com.paparazziteam.securityapplicationapp.usecases.GetPokemonUseCase
import com.paparazziteam.securityapplicationapp.usecases.PokemonState
import com.scottyab.rootbeer.RootBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _statePokemon:MutableStateFlow<PokemonState> = MutableStateFlow(PokemonState.Idle)
    val statePokemon: StateFlow<PokemonState> = _statePokemon

    private val isVisibleBypassHttps = MutableStateFlow<VisibleStateWith>(VisibleStateWith())
    val isVisibleBypassHttpsState: StateFlow<VisibleStateWith> = isVisibleBypassHttps

    private val isVisibleBypassHttp = MutableStateFlow<VisibleStateWith>(VisibleStateWith())
    val isVisibleBypassHttpState: StateFlow<VisibleStateWith> = isVisibleBypassHttp

    private val isVisibleBypassCertificatePinning = MutableStateFlow<VisibleStateWith>(
        VisibleStateWith()
    )
    val isVisibleBypassCertificatePinningState: StateFlow<VisibleStateWith> = isVisibleBypassCertificatePinning

    //Root
    private val isVisibleRootDetection = MutableStateFlow<VisibleStateWith>(VisibleStateWith())
    val isVisibleRootDetectionState: StateFlow<VisibleStateWith> = isVisibleRootDetection

    //HTTP
    fun getPokemonInfoHttp(name:String) = viewModelScope.launch(Dispatchers.IO) {
        getPokemonUseCase
            .getPokemonHttp(name)
            .onStart {
                isVisibleBypassHttp.value = VisibleStateWith(false)
                _statePokemon.value = PokemonState.ShowLoading
            }.onEach {
                withContext(Dispatchers.Main){
                    _statePokemon.value = PokemonState.Success(it)
                    isVisibleBypassHttp.value = VisibleStateWith(true,R.drawable.ic_check_circle)
                }
            }.catch {
                withContext(Dispatchers.Main){
                    isVisibleBypassHttp.value = VisibleStateWith(true, R.drawable.ic_error)
                    _statePokemon.value = PokemonState.Error(it)
                }
            }.launchIn(viewModelScope)
    }

    //HTTPS
    //Dispatchers.IO -> Coroutines para operaciones de red, lectura y escritura de archivos
    fun getPokemonInfo(name:String) = viewModelScope.launch(Dispatchers.IO) {
        getPokemonUseCase
            .invoke(name)
            .onStart {
                isVisibleBypassHttps.value = VisibleStateWith(false)
                _statePokemon.value = PokemonState.ShowLoading
            }.onEach {
                withContext(Dispatchers.Main){
                    _statePokemon.value = PokemonState.Success(it)
                    isVisibleBypassHttps.value = VisibleStateWith(true,R.drawable.ic_check_circle)
                }
            }.catch {
                withContext(Dispatchers.Main){
                    isVisibleBypassHttps.value = VisibleStateWith(true, R.drawable.ic_error)
                    _statePokemon.value = PokemonState.Error(it)
                }
            }.launchIn(viewModelScope)
    }

    //HTTPS + Certificate Pinning
    fun getPokemonInfoSsl(name:String) = viewModelScope.launch(Dispatchers.IO) {
        getPokemonUseCase
            .getPokemonInfoSsl(name)
            .onStart {
                isVisibleBypassCertificatePinning.value = VisibleStateWith(false)
                _statePokemon.value = PokemonState.ShowLoading
            }.onEach {
                withContext(Dispatchers.Main){
                    _statePokemon.value = PokemonState.Success(it)
                    isVisibleBypassCertificatePinning.value = VisibleStateWith(true,R.drawable.ic_check_circle)
                }
            }.catch {
                withContext(Dispatchers.Main){
                    isVisibleBypassCertificatePinning.value = VisibleStateWith(true, R.drawable.ic_error)
                    _statePokemon.value = PokemonState.Error(it)
                }
            }.launchIn(viewModelScope)
    }

    fun checkRootDetection(context: Context){
        val rootDetector = RootBeer(context)
        val isRooted = rootDetector.isRootedWithBusyBoxCheck
        val isRootedWithSu = rootDetector.isRooted
        isVisibleRootDetection.value = VisibleStateWith(true, if(isRooted || isRootedWithSu) R.drawable.ic_error else R.drawable.ic_check_circle)
    }


    fun openYoutube(context: Context) = viewModelScope.launch {
        val url = context.getString(R.string.youtube_channel)
        openLink(context, url)
    }


    data class VisibleStateWith(val isVisible :Boolean = false, val icon:Int?= null) {
        val isVisibleIcon: Boolean = isVisible
        val iconResource: Int? = icon
    }
}