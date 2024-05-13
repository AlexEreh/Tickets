package ru.alexereh.tickets.data.remote.selectedsearch.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.selectedsearch.dto.TicketsOffersDTO

interface TicketsOffersRemoteApi {
    @GET("07bfc68c532f32530919")
    suspend fun getOffersTickets(): TicketsOffersDTO
}