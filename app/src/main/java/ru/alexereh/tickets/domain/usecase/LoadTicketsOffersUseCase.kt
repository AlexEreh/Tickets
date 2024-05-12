package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadTicketsOffersUseCase @Inject constructor(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke(): Flow<TicketsOffersModel> {
        return repository.getTicketsOffers()
    }
}