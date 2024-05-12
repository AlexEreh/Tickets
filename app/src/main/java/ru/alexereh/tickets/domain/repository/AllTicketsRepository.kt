package ru.alexereh.tickets.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsModel

interface AllTicketsRepository {
    fun getTickets(): Flow<TicketsModel>
}