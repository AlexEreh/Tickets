package ru.alexereh.tickets.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.alexereh.tickets.data.remote.alltickets.api.AllTicketsRemoteApi
import ru.alexereh.tickets.data.remote.selectedsearch.api.TicketsOffersRemoteApi
import ru.alexereh.tickets.data.remote.tickets.api.OffersRemoteApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(json: Json): Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://api.npoint.io/")
        .build()

    @Provides
    @Singleton
    fun provideOffersRemoteApi(
        retrofit: Retrofit
    ): OffersRemoteApi = retrofit.create(OffersRemoteApi::class.java)

    @Provides
    @Singleton
    fun provideTicketsOffersRemoteApi(
        retrofit: Retrofit
    ): TicketsOffersRemoteApi = retrofit.create(TicketsOffersRemoteApi::class.java)

    @Provides
    @Singleton
    fun provideAllTicketsRemoteApi(
        retrofit: Retrofit
    ): AllTicketsRemoteApi = retrofit.create(AllTicketsRemoteApi::class.java)
}