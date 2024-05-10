package ru.alexereh.tickets.data.remote.tickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.tickets.dto.OffersDTO

interface OffersRemoteApi {
    @GET("first")
    suspend fun getOffers(): OffersDTO
}