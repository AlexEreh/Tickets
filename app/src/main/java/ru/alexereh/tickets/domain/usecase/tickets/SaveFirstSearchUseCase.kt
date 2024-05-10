package ru.alexereh.tickets.domain.usecase.tickets

import ru.alexereh.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class SaveFirstSearchUseCase @Inject constructor(
    private val repository: TicketsRepository
) {
    operator fun invoke(text: String) {
        repository.firstSearch(text)
    }
}