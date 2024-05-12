package ru.alexereh.tickets.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.alexereh.tickets.domain.usecase.LoadFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.SaveSecondSearchUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    loadFirstSearchUseCase: LoadFirstSearchUseCase,
    private val saveSecondSearchUseCase: SaveSecondSearchUseCase
) : ViewModel() {
    private val _firstSearch = loadFirstSearchUseCase()
    val firstSearch = _firstSearch
        .stateIn(this.viewModelScope, SharingStarted.Eagerly, "")

    fun saveSecondSearch(text: String) {
        saveSecondSearchUseCase(text)
    }
}