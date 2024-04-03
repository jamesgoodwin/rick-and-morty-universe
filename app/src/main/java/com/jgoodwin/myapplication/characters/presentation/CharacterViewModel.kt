package com.jgoodwin.myapplication.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgoodwin.myapplication.characters.domain.CharacterSummary
import com.jgoodwin.myapplication.characters.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    private val mutableState = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> get() = mutableState

    init {
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            mutableState.value = State.Success(characters)
        }
    }

    sealed class State {
        data object Loading : State()
        class Success(val results: List<CharacterSummary>) : State()
    }

}

