package ru.alexereh.tickets.data.remote.tickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersDTO(
    @SerialName("offers")
    val offers: List<OfferDTO>
)