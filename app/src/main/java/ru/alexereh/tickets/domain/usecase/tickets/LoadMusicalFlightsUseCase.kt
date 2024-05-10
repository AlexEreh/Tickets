package ru.alexereh.tickets.domain.usecase.tickets

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.OffersModel
import ru.alexereh.tickets.domain.repository.TicketsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadMusicalFlightsUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    operator fun invoke(): Flow<OffersModel> {
        return repository.getOffers()
    }
}