package com.jgoodwin.myapplication.episodes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgoodwin.myapplication.characters.domain.CharacterSummary
import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel
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

    private val mutableState = MutableStateFlow<EpisodesViewState>(EpisodesViewState.Loading)
    val state: StateFlow<EpisodesViewState> get() = mutableState

    init {
        viewModelScope.launch {
            val episodes = getEpisodesUseCase.invoke()
            mutableState.value = EpisodesViewState.Success(episodes)
        }
    }

    sealed class EpisodesViewState {
        data object Loading : EpisodesViewState()
        class Error(val message: String) : EpisodesViewState()
        class Success(val results: List<Episode>) : EpisodesViewState()
    }

}