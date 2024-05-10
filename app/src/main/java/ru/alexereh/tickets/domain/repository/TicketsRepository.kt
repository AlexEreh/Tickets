package ru.alexereh.tickets.domain.repository

interface TicketsRepository {
    fun firstSearch(text: String)
}