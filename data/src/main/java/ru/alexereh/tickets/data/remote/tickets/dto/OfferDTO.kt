package ru.alexereh.tickets.data.remote.tickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("town")
    val town: String,
    @SerialName("price")
    val price: PriceDTO
)
