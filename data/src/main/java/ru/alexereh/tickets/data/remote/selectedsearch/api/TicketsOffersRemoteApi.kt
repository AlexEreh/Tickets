package ru.alexereh.tickets.data.remote.selectedsearch.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.selectedsearch.dto.TicketsOffersDTO

interface TicketsOffersRemoteApi {
    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun getOffersTickets(): TicketsOffersDTO
}