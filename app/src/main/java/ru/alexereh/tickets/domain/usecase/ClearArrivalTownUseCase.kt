package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClearArrivalTownUseCase @Inject constructor(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke() {
        repository.clearArrivalTown()
    }
}