package ru.alexereh.tickets.data.repository.search

import kotlinx.coroutines.flow.Flow
import ru.alexereh.tickets.data.local.search.SearchLocalDataSource
import ru.alexereh.tickets.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource
): SearchRepository {
    override fun getFirstSearch(): Flow<String> {
        return searchLocalDataSource.getFirstSearch()
    }
}