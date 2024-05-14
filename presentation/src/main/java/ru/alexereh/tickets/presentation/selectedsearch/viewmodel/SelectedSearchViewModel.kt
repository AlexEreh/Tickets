package ru.alexereh.tickets.presentation.selectedsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import ru.alexereh.tickets.domain.usecase.ClearArrivalTownUseCase
import ru.alexereh.tickets.domain.usecase.LoadDepartureDateUseCase
import ru.alexereh.tickets.domain.usecase.LoadFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadReturnDateUseCase
import ru.alexereh.tickets.domain.usecase.LoadSecondSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadTicketsOffersUseCase
import ru.alexereh.tickets.domain.usecase.SaveDepartureDateUseCase
import ru.alexereh.tickets.domain.usecase.SaveReturnDateUseCase
import ru.alexereh.tickets.domain.usecase.SwapDepratureArrivalTownsUseCase
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SelectedSearchViewModel @Inject constructor(
    private val swapDepratureArrivalTownsUseCase: SwapDepratureArrivalTownsUseCase,
    private val clearArrivalTownUseCase: ClearArrivalTownUseCase,
    private val loadSecondSearchUseCase: LoadSecondSearchUseCase,
    private val loadFirstSearchUseCase: LoadFirstSearchUseCase,
    private val loadTicketsOffersUseCase: LoadTicketsOffersUseCase,
    private val saveDepartureDateUseCase: SaveDepartureDateUseCase,
    private val loadDepartureDateUseCase: LoadDepartureDateUseCase,
    private val saveReturnDateUseCase: SaveReturnDateUseCase,
    private val loadReturnDateUseCase: LoadReturnDateUseCase
) : ViewModel() {
    private val _firstSearch = loadFirstSearchUseCase()
    val firstSearch = _firstSearch
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    private val _secondSearch = loadSecondSearchUseCase()
    val secondSearch = _secondSearch
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    private val _ticketsOffers = loadTicketsOffersUseCase()
    val ticketsOffers = _ticketsOffers
        .stateIn(viewModelScope, SharingStarted.Eagerly, TicketsOffersModel(emptyList()))

    private val _departureDate = loadDepartureDateUseCase()
    val departureDate = _departureDate
        .stateIn(viewModelScope, SharingStarted.Eagerly, LocalDate.now())

    private val _returnDate = loadReturnDateUseCase()
    val returnDate = _returnDate
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun swapDepratureArrivalTowns() {
        swapDepratureArrivalTownsUseCase()
    }

    fun clearArrivalTown() {
        clearArrivalTownUseCase()
    }

    fun saveDepartureDate(date: LocalDate) {
        saveDepartureDateUseCase(date)
    }

    fun saveReturnDate(date: LocalDate?) {
        saveReturnDateUseCase(date)
    }
}