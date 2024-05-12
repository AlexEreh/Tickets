package ru.alexereh.tickets.data.repository.selectedsearch

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.LocalDataSource
import ru.alexereh.tickets.data.remote.selectedsearch.TicketsOffersRemoteDataSource
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectedSearchRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: TicketsOffersRemoteDataSource
) : SelectedSearchRepository {
    override fun saveSecondSearch(text: String) {
        localDataSource.saveSecondSearch(text)
    }

    override fun getSecondSearch(): Flow<String> {
        return localDataSource.getSecondSearch()
    }

    override fun clearArrivalTown() {
        localDataSource.saveSecondSearch("")
    }

    override fun swapDepartureArrivalTowns() {
        localDataSource.swapDepartureArrivalTowns()
    }

    override fun getTicketsOffers(): Flow<TicketsOffersModel> {
        return remoteDataSource.getOffersTickets()
    }
}