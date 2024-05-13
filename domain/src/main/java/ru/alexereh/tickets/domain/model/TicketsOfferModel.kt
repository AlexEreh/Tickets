package ru.alexereh.tickets.domain.model

data class TicketsOfferModel(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: PriceModel
)