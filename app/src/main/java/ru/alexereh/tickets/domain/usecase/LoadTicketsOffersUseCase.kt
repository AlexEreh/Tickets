package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository

class LoadTicketsOffersUseCase(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke(): Flow<TicketsOffersModel> {
        return repository.getTicketsOffers()
    }
}