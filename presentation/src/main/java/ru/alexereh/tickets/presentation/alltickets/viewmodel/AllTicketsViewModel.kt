package ru.alexereh.tickets.presentation.alltickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import ru.alexereh.tickets.domain.model.TicketsModel
import ru.alexereh.tickets.domain.usecase.LoadFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadSecondSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadTicketsUseCase
import javax.inject.Inject

@HiltViewModel
class AllTicketsViewModel @Inject constructor(
    private val loadFirstSearchUseCase: LoadFirstSearchUseCase,
    private val loadSecondSearchUseCase: LoadSecondSearchUseCase,
    private val loadTicketsUseCase: LoadTicketsUseCase
) : ViewModel() {
    private val _firstSearch = loadFirstSearchUseCase()

    private val _secondSearch = loadSecondSearchUseCase()
    private val _tickets = loadTicketsUseCase()
    val destinations = _firstSearch
        .zip(_secondSearch) { first, second ->
            "$first-$second"
        }

    val tickets = _tickets
        .stateIn(viewModelScope, SharingStarted.Eagerly, TicketsModel(emptyList()))
}