package ru.alexereh.tickets.domain.usecase.search

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.SearchRepository
import javax.inject.Inject

class LoadFirstSearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getFirstSearch()
    }
}