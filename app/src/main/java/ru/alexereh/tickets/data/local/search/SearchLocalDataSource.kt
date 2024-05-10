package ru.alexereh.tickets.data.local.search

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.alexereh.tickets.data.local.DataStorePrefencesKeys.FIRST_SEARCH_KEY
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val preferences: DataStore<Preferences>
) {
    fun getFirstSearch(): Flow<String> {
        return preferences.data.map {
            it[FIRST_SEARCH_KEY]!!
        }
    }
}