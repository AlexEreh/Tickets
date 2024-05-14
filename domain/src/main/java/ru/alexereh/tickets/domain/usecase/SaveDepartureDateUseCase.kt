package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import java.time.LocalDate

class SaveDepartureDateUseCase(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke(date: LocalDate) {
        repository.saveDepartureDate(date)
    }
}