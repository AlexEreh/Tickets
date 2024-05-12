package ru.alexereh.tickets.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadSecondSearchUseCase @Inject constructor(
    private val repository: SelectedSearchRepository
) {
    operator fun invoke(): Flow<String> {
        return repository.getSecondSearch()
    }
}