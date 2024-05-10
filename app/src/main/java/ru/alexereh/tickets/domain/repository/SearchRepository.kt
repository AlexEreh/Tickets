package ru.alexereh.tickets.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getFirstSearch(): Flow<String>
}