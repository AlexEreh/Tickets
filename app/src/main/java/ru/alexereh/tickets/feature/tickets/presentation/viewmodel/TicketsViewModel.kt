package ru.alexereh.tickets.feature.tickets.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexereh.tickets.domain.usecase.tickets.SaveFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.tickets.LoadMusicalFlightsUseCase
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val loadMusicalFlightsUseCase: LoadMusicalFlightsUseCase,
    private val saveFirstSearchUseCase: SaveFirstSearchUseCase,
): ViewModel() {
    fun onFirstSearch(text: String) {
        saveFirstSearchUseCase(text)
    }
}