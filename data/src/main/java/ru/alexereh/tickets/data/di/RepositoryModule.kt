package ru.alexereh.tickets.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexereh.tickets.data.repository.alltickets.AllTicketsRepositoryImpl
import ru.alexereh.tickets.data.repository.search.SearchRepositoryImpl
import ru.alexereh.tickets.data.repository.selectedsearch.SelectedSearchRepositoryImpl
import ru.alexereh.tickets.data.repository.tickets.TicketsRepositoryImpl
import ru.alexereh.tickets.domain.repository.AllTicketsRepository
import ru.alexereh.tickets.domain.repository.SearchRepository
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import ru.alexereh.tickets.domain.repository.TicketsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTicketsRepository(
        ticketsRepositoryImpl: TicketsRepositoryImpl
    ): TicketsRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    abstract fun bindSelectedSearch(
        selectedSearchRepositoryImpl: SelectedSearchRepositoryImpl
    ): SelectedSearchRepository

    @Binds
    abstract fun bindAllTicketsRepository(
        allTicketsRepositoryImpl: AllTicketsRepositoryImpl
    ): AllTicketsRepository
}