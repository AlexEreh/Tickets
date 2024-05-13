package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsModel
import ru.alexereh.tickets.domain.repository.AllTicketsRepository

class LoadTicketsUseCase(
    private val repository: AllTicketsRepository
) {
    operator fun invoke(): Flow<TicketsModel> {
        return repository.getTickets()
    }
}