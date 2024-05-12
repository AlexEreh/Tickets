package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveSecondSearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(text: String) {
        repository.saveSecondSearch(text)
    }
}