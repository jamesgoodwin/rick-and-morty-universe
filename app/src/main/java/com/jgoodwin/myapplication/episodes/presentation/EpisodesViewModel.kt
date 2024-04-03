package com.jgoodwin.myapplication.episodes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgoodwin.myapplication.episodes.domain.GetEpisodesUseCase
import com.jgoodwin.myapplication.episodes.domain.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val getEpisodesUseCase: GetEpisodesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(emptyList<Episode>())
    val state: StateFlow<List<Episode>> get() = _state

    init {
        viewModelScope.launch {
            val episodes = getEpisodesUseCase.invoke()
            _state.value = episodes
        }
    }

}