package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.SelectedSearchRepository

class ClearArrivalTownUseCase(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke() {
        repository.clearArrivalTown()
    }
}