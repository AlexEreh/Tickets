package ru.alexereh.tickets.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import ru.alexereh.tickets.data.local.LocalDataSource
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_prefs"

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() },
            ),
            migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES)),
            produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        preferences: DataStore<Preferences>,
        ioScope: CoroutineScope
    ): LocalDataSource {
        return LocalDataSource(preferences, ioScope)
    }
}