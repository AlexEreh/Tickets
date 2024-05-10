package ru.alexereh.tickets.data.repository.tickets

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.tickets.TicketsLocalDataSource
import ru.alexereh.tickets.data.remote.tickets.OffersRemoteDataSource
import ru.alexereh.tickets.domain.model.OffersModel
import ru.alexereh.tickets.domain.repository.TicketsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TicketsRepositoryImpl @Inject constructor(
    private val ticketsLocalDataSource: TicketsLocalDataSource,
    private val offersRemoteDataSource: OffersRemoteDataSource
): TicketsRepository {
    override fun firstSearch(text: String) {
        ticketsLocalDataSource.saveFirstSearch(text)
    }

    override fun getOffers(): Flow<OffersModel> {
        return offersRemoteDataSource.getTickets()
    }
}