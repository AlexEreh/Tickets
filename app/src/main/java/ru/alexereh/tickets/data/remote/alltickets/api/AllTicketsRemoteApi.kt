package ru.alexereh.tickets.data.remote.alltickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.alltickets.dto.TicketsDTO

interface AllTicketsRemoteApi {
    @GET("https://tickets-alexereh.free.beeceptor.com/third")
    fun getTickets(): TicketsDTO
}