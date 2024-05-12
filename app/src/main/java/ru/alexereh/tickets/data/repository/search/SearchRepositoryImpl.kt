package ru.alexereh.tickets.data.repository.search

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.LocalDataSource
import ru.alexereh.tickets.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: LocalDataSource
): SearchRepository {
    override fun getFirstSearch(): Flow<String> {
        return searchLocalDataSource.getFirstSearch()
    }

    override fun saveSecondSearch(text: String) {
        searchLocalDataSource.saveSecondSearch(text)
    }
}