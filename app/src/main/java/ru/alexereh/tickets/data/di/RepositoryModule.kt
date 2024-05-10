package ru.alexereh.tickets.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexereh.tickets.data.repository.search.SearchRepositoryImpl
import ru.alexereh.tickets.data.repository.tickets.TicketsRepositoryImpl
import ru.alexereh.tickets.domain.repository.SearchRepository
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
}