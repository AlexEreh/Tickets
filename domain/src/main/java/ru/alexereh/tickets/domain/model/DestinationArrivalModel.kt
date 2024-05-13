package ru.alexereh.tickets.domain.model

import java.time.LocalDateTime

data class DestinationArrivalModel(
    val town: String,
    val date: LocalDateTime,
    val airport: String
)