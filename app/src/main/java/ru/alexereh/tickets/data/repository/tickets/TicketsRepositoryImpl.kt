package ru.alexereh.tickets.data.repository.tickets

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.tickets.TicketsLocalDataSource
import ru.alexereh.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketsLocalDataSource: TicketsLocalDataSource
): TicketsRepository {
    override fun firstSearch(text: String) {
        ticketsLocalDataSource.saveFirstSearch(text)
    }
}