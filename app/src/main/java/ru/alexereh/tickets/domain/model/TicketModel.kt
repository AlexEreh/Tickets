package ru.alexereh.tickets.domain.model

data class TicketModel(
    val id: Int,
    val badge: String,
    val price: PriceModel,
    val providerName: String,
    val company: String,
    val departure: DestinationArrivalModel,
    val arrival: DestinationArrivalModel,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: LuggageModel,
    val handLuggage: HandLuggageModel,
    val isReturnable: Boolean,
    val isExchangeable: Boolean
)