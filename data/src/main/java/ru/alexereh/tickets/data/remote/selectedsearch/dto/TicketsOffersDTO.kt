package ru.alexereh.tickets.data.remote.selectedsearch.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffersDTO(
    @SerialName("tickets_offers")
    val ticketsOffers: List<TicketsOfferDTO>
)