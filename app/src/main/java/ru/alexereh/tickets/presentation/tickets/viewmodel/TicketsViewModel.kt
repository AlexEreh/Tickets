package ru.alexereh.tickets.presentation.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.alexereh.tickets.domain.model.OffersModel
import ru.alexereh.tickets.domain.usecase.LoadFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadOffersUseCase
import ru.alexereh.tickets.domain.usecase.SaveFirstSearchUseCase
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val loadOffersUseCase: LoadOffersUseCase,
    private val saveFirstSearchUseCase: SaveFirstSearchUseCase,
    private val loadFirstSearchUseCase: LoadFirstSearchUseCase
): ViewModel() {
    private val _offers = loadOffersUseCase()
    val offers: StateFlow<OffersModel> = _offers
        .stateIn(viewModelScope, SharingStarted.Eagerly, OffersModel(emptyList()))

    private val _firstSearch = loadFirstSearchUseCase()
    val firstSearch = _firstSearch
        .stateIn(this.viewModelScope, SharingStarted.Eagerly, "")

    fun onFirstSearch(search: String) {
        saveFirstSearchUseCase(search)
    }
}