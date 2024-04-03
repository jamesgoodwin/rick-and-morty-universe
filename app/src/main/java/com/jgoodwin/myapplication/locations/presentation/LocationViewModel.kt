package com.jgoodwin.myapplication.locations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgoodwin.myapplication.locations.domain.GetLocationsUseCase
import com.jgoodwin.myapplication.locations.domain.LocationDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(emptyList<LocationDetail>())
    val state: StateFlow<List<LocationDetail>> get() = _state

    init {
        viewModelScope.launch {
            val characters = getLocationsUseCase.invoke()
            _state.value = characters
        }
    }

}