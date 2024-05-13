package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.SearchRepository

class LoadFirstSearchUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getFirstSearch()
    }
}