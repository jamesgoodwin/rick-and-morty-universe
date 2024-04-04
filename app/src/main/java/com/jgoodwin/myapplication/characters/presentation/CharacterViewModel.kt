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

    private val mutableState = MutableStateFlow<CharacterViewState>(CharacterViewState.Loading)
    val state: StateFlow<CharacterViewState> get() = mutableState

    init {
        viewModelScope.launch {
            val characters = getCharactersUseCase.invoke()
            mutableState.value = CharacterViewState.Success(characters)
        }
    }

    sealed class CharacterViewState {
        data object Loading : CharacterViewState()
        class Error(val message: String) : CharacterViewState()
        class Success(val results: List<CharacterSummary>) : CharacterViewState()
    }

}

