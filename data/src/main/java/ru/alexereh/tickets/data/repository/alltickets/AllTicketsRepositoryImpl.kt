package ru.alexereh.tickets.data.repository.alltickets

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.LocalDataSource
import ru.alexereh.tickets.data.remote.alltickets.AllTicketsRemoteDataSource
import ru.alexereh.tickets.domain.model.TicketsModel
import ru.alexereh.tickets.domain.repository.AllTicketsRepository
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllTicketsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AllTicketsRemoteDataSource,
    private val localDataSource: LocalDataSource
) : AllTicketsRepository {
    override fun getTickets(): Flow<TicketsModel> {
        return remoteDataSource.getTickets()
    }

    override fun loadDepartureDate(): Flow<LocalDate> {
        return localDataSource.getDepartureDate()
    }

    override fun loadReturnDate(): Flow<LocalDate?> {
        return localDataSource.getReturnDate()
    }
}