package ru.alexereh.tickets.domain.model


data class OfferModel(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceModel
)