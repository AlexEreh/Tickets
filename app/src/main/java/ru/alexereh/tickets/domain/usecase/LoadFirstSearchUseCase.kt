package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadFirstSearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getFirstSearch()
    }
}