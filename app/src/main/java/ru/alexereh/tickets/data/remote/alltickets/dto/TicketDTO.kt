package ru.alexereh.tickets.data.remote.alltickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("badge")
    val badge: String,
    @SerialName("price")
    val price: PriceDTO,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("company")
    val company: String,
    @SerialName("departure")
    val departure: DestinationArrivalDTO,
    @SerialName("arrival")
    val arrival: DestinationArrivalDTO,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerialName("luggage")
    val luggage: LuggageDTO,
    @SerialName("hand_luggage")
    val handLuggage: HandLuggageDTO,
    @SerialName("is_returnable")
    val isReturnable: Boolean,
    @SerialName("is_exchangable")
    val isExchangeable: Boolean
)
