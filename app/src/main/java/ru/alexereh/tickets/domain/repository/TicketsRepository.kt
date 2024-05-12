package ru.alexereh.tickets.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.model.OffersModel

interface TicketsRepository {
    fun saveFirstSearch(text: String)
    fun getOffers(): Flow<OffersModel>
}