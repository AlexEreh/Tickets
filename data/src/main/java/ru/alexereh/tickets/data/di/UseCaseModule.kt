package ru.alexereh.tickets.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexereh.tickets.domain.repository.AllTicketsRepository
import ru.alexereh.tickets.domain.repository.SearchRepository
import ru.alexereh.tickets.domain.repository.SelectedSearchRepository
import ru.alexereh.tickets.domain.repository.TicketsRepository
import ru.alexereh.tickets.domain.usecase.ClearArrivalTownUseCase
import ru.alexereh.tickets.domain.usecase.LoadDepartureDateUseCase
import ru.alexereh.tickets.domain.usecase.LoadFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadOffersUseCase
import ru.alexereh.tickets.domain.usecase.LoadReturnDateUseCase
import ru.alexereh.tickets.domain.usecase.LoadSecondSearchUseCase
import ru.alexereh.tickets.domain.usecase.LoadTicketsOffersUseCase
import ru.alexereh.tickets.domain.usecase.LoadTicketsUseCase
import ru.alexereh.tickets.domain.usecase.SaveDepartureDateUseCase
import ru.alexereh.tickets.domain.usecase.SaveFirstSearchUseCase
import ru.alexereh.tickets.domain.usecase.SaveReturnDateUseCase
import ru.alexereh.tickets.domain.usecase.SaveSecondSearchUseCase
import ru.alexereh.tickets.domain.usecase.SwapDepratureArrivalTownsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideClearArrivalTownUseCase(
        repository: SelectedSearchRepository
    ): ClearArrivalTownUseCase {
        return ClearArrivalTownUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadFirstSearchUseCase(
        repository: SearchRepository
    ): LoadFirstSearchUseCase {
        return LoadFirstSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadOffersUseCase(
        repository: TicketsRepository
    ): LoadOffersUseCase {
        return LoadOffersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadSecondSearchUseCase(
        repository: SelectedSearchRepository
    ): LoadSecondSearchUseCase {
        return LoadSecondSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadTicketsOffersUseCase(
        repository: SelectedSearchRepository
    ): LoadTicketsOffersUseCase {
        return LoadTicketsOffersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadTicketsUseCase(
        repository: AllTicketsRepository
    ): LoadTicketsUseCase {
        return LoadTicketsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveFirstSearchUseCase(
        repository: TicketsRepository
    ): SaveFirstSearchUseCase {
        return SaveFirstSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveSecondSearchUseCase(
        repository: SearchRepository
    ): SaveSecondSearchUseCase {
        return SaveSecondSearchUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSwapDepartureArrivalTownsUseCase(
        repository: SelectedSearchRepository
    ): SwapDepratureArrivalTownsUseCase {
        return SwapDepratureArrivalTownsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveDepartureDateUseCase(
        repository: SelectedSearchRepository
    ): SaveDepartureDateUseCase {
        return SaveDepartureDateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadDepartureDateUseCase(
        repository: AllTicketsRepository
    ): LoadDepartureDateUseCase {
        return LoadDepartureDateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveReturnDateUseCase(
        repository: SelectedSearchRepository
    ): SaveReturnDateUseCase {
        return SaveReturnDateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadReturnDateUseCase(
        repository: AllTicketsRepository
    ): LoadReturnDateUseCase {
        return LoadReturnDateUseCase(repository)
    }
}