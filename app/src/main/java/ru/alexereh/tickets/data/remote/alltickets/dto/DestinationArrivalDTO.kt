package ru.alexereh.tickets.data.remote.alltickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationArrivalDTO(
    @SerialName("town")
    val town: String,
    @SerialName("date")
    val date: String,
    @SerialName("airport")
    val airport: String
)
