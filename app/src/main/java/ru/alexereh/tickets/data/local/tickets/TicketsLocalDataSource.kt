package ru.alexereh.tickets.data.local.tickets

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.runBlocking
import ru.alexereh.tickets.data.local.DataStorePrefencesKeys.FIRST_SEARCH_KEY
import javax.inject.Inject


class TicketsLocalDataSource @Inject constructor(
    private val preferences: DataStore<Preferences>
) {
    fun saveFirstSearch(text: String) {
        runBlocking {
            preferences.edit {
                it[FIRST_SEARCH_KEY] = text
            }
        }
    }
}