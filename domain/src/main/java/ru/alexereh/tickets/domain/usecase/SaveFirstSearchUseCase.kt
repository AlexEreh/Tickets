package ru.alexereh.tickets.domain.usecase

import ru.alexereh.tickets.domain.repository.TicketsRepository

class SaveFirstSearchUseCase(
    private val repository: TicketsRepository
) {
    operator fun invoke(text: String) {
        repository.saveFirstSearch(text)
    }
}