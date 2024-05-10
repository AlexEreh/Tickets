package ru.alexereh.tickets.data.remote.alltickets.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HandLuggageDTO(
    @SerialName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String
)
