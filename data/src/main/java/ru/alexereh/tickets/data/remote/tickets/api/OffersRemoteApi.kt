package ru.alexereh.tickets.data.remote.tickets.api

import retrofit2.http.GET
import ru.alexereh.tickets.data.remote.tickets.dto.OffersDTO

interface OffersRemoteApi {
    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getOffers(): OffersDTO
}