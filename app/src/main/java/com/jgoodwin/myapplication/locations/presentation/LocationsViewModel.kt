package com.jgoodwin.myapplication.locations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel
import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.locations.domain.GetLocationsUseCase
import com.jgoodwin.myapplication.locations.domain.LocationDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) :
    ViewModel() {

    private val mutableState = MutableStateFlow<LocationsViewState>(LocationsViewState.Loading)
    val state: StateFlow<LocationsViewState> get() = mutableState

    init {
        viewModelScope.launch {
            val characters = getLocationsUseCase.invoke()
            mutableState.value = LocationsViewState.Success(characters)
        }
    }

    sealed class LocationsViewState {
        data object Loading : LocationsViewState()
        class Error(val message: String) : LocationsViewState()
        class Success(val results: List<LocationDetail>) : LocationsViewState()
    }

}