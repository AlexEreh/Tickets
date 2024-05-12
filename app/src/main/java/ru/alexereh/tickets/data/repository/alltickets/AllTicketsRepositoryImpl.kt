package ru.alexereh.tickets.data.repository.alltickets

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.remote.alltickets.AllTicketsRemoteDataSource
import ru.alexereh.tickets.domain.model.TicketsModel
import ru.alexereh.tickets.domain.repository.AllTicketsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllTicketsRepositoryImpl @Inject constructor(
    private val dataSource: AllTicketsRemoteDataSource
) : AllTicketsRepository {
    override fun getTickets(): Flow<TicketsModel> {
        return dataSource.getTickets()
    }
}