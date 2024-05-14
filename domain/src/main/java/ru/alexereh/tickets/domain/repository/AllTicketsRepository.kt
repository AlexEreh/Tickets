package ru.alexereh.tickets.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsModel
import java.time.LocalDate

interface AllTicketsRepository {
    fun getTickets(): Flow<TicketsModel>
    fun loadDepartureDate(): Flow<LocalDate>
    fun loadReturnDate(): Flow<LocalDate?>
}