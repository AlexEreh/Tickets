package ru.alexereh.tickets.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.TicketsOffersModel
import java.time.LocalDate

interface SelectedSearchRepository {
    fun saveSecondSearch(text: String)
    fun getSecondSearch(): Flow<String>
    fun clearArrivalTown()
    fun swapDepartureArrivalTowns()
    fun getTicketsOffers(): Flow<TicketsOffersModel>
    fun saveDepartureDate(date: LocalDate)
    fun saveReturnDate(date: LocalDate?)
}