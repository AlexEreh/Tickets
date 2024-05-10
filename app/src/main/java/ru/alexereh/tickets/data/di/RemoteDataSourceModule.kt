package ru.alexereh.tickets.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.alexereh.tickets.data.remote.tickets.api.OffersRemoteApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://tickets-alexereh.free.beeceptor.com/")
        .build()

    @Provides
    @Singleton
    fun provideOffersRemoteApi(
        retrofit: Retrofit
    ): OffersRemoteApi = retrofit.create(OffersRemoteApi::class.java)
}