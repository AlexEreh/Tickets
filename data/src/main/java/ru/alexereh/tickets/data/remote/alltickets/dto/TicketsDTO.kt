package ru.alexereh.tickets.data.remote.alltickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsDTO(
    @SerialName("tickets")
    val tickets: List<TicketDTO>
)