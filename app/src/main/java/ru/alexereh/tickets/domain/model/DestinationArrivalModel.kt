package ru.alexereh.tickets.domain.model

import java.time.LocalDate

data class DestinationArrivalModel(
    val town: String,
    val date: LocalDate,
    val airport: String
)