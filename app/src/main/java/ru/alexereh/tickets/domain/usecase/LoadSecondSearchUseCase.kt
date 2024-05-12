package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository

class LoadSecondSearchUseCase(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getSecondSearch()
    }
}