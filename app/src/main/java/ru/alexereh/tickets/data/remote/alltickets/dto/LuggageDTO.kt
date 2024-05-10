package ru.alexereh.tickets.data.remote.alltickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LuggageDTO(
    @SerialName("has_luggage")
    val hasLuggage: Boolean,
    @SerialName("price")
    val price: PriceDTO
)
