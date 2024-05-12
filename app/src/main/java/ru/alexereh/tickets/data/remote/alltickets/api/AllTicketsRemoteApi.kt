package ru.alexereh.tickets.data.remote.alltickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.alltickets.dto.TicketsDTO

interface AllTicketsRemoteApi {
    @GET("a7f466047728e5daafde")
    suspend fun getTickets(): TicketsDTO
}