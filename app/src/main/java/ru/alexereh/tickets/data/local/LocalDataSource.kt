package ru.alexereh.tickets.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val preferences: DataStore<Preferences>,
    private val ioScope: CoroutineScope
) {
    fun saveFirstSearch(text: String) {
        ioScope.launch {
            preferences.edit {
                it[DataStorePrefencesKeys.FIRST_SEARCH_KEY] = text
            }
        }
    }

    fun getSecondSearch(): Flow<String> {
        return preferences.data.map {
            it[DataStorePrefencesKeys.SECOND_SEARCH_KEY] ?: ""
        }
    }

    fun getFirstSearch(): Flow<String> {
        return preferences.data.map {
            it[DataStorePrefencesKeys.FIRST_SEARCH_KEY] ?: ""
        }
    }

    fun saveSecondSearch(text: String) {
        ioScope.launch {
            preferences.edit {
                it[DataStorePrefencesKeys.SECOND_SEARCH_KEY] = text
            }
        }
    }

    fun swapDepartureArrivalTowns() {
        ioScope.launch {
            preferences.edit {
                val first = it[DataStorePrefencesKeys.FIRST_SEARCH_KEY]!!
                val second = it[DataStorePrefencesKeys.SECOND_SEARCH_KEY]!!
                it[DataStorePrefencesKeys.FIRST_SEARCH_KEY] = second
                it[DataStorePrefencesKeys.SECOND_SEARCH_KEY] = first
            }
        }
    }
}