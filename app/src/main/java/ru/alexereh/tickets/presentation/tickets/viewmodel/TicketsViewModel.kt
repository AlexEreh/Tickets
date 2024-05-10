package ru.alexereh.tickets.presentation.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.alexereh.tickets.domain.model.OffersModel
import ru.alexereh.tickets.domain.usecase.tickets.LoadMusicalFlightsUseCase
import ru.alexereh.tickets.domain.usecase.tickets.SaveFirstSearchUseCase
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val loadMusicalFlightsUseCase: LoadMusicalFlightsUseCase,
    private val saveFirstSearchUseCase: SaveFirstSearchUseCase,
): ViewModel() {
    private val _musicalFlights = loadMusicalFlightsUseCase()
    val musicalFlights: StateFlow<OffersModel> = _musicalFlights
        .stateIn(viewModelScope, SharingStarted.Eagerly, OffersModel(emptyList()))

    fun onFirstSearch(search: String) {
        saveFirstSearchUseCase(search)
    }
}