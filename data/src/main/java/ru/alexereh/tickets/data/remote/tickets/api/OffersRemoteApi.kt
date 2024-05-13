package ru.alexereh.tickets.data.remote.tickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.tickets.dto.OffersDTO

interface OffersRemoteApi {
    @GET("4ad13708ee2178cfc3c3")
    suspend fun getOffers(): OffersDTO
}