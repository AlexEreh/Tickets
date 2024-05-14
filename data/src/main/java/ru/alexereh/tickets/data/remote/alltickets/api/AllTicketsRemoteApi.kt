package ru.alexereh.tickets.data.remote.alltickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.alltickets.dto.TicketsDTO

interface AllTicketsRemoteApi {
    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun getTickets(): TicketsDTO
}