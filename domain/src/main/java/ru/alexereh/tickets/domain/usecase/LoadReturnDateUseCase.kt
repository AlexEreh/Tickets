package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.AllTicketsRepository
import java.time.LocalDate

class LoadReturnDateUseCase(
    private val repository: AllTicketsRepository
) {
    operator fun invoke(): Flow<LocalDate?> {
        return repository.loadReturnDate()
    }
}