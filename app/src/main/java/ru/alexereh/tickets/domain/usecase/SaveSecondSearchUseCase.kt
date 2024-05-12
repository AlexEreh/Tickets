package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.SearchRepository

class SaveSecondSearchUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(text: String) {
        repository.saveSecondSearch(text)
    }
}