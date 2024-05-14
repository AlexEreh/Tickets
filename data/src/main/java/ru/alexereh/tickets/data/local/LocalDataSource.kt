package ru.alexereh.tickets.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

class LocalDataSource(
    private val preferences: DataStore<Preferences>,
    private val ioScope: CoroutineScope
) {
    fun saveFirstSearch(text: String) {
        runBlocking {
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
        runBlocking {
            preferences.edit {
                it[DataStorePrefencesKeys.SECOND_SEARCH_KEY] = text
            }
        }
    }

    fun swapDepartureArrivalTowns() {
        runBlocking {
            preferences.edit {
                val first = it[DataStorePrefencesKeys.FIRST_SEARCH_KEY]!!
                val second = it[DataStorePrefencesKeys.SECOND_SEARCH_KEY]!!
                it[DataStorePrefencesKeys.FIRST_SEARCH_KEY] = second
                it[DataStorePrefencesKeys.SECOND_SEARCH_KEY] = first
            }
        }
    }

    fun getDepartureDate(): Flow<LocalDate> {
        return preferences.data.map {
            val a = it[DataStorePrefencesKeys.DEPARTURE_DATE_KEY]
            if (a == null) {
                return@map LocalDate.now()
            }
            LocalDate.parse(a)
        }
    }

    fun saveDepartureDate(date: LocalDate) {
        runBlocking {
            preferences.edit {
                it[DataStorePrefencesKeys.DEPARTURE_DATE_KEY] = date.toString()
            }
        }
    }

    fun getReturnDate(): Flow<LocalDate?> {
        return preferences.data.map {
            val a = it[DataStorePrefencesKeys.RETURN_DATE_KEY]
            if (a == null || a == "null") {
                return@map null
            }
            LocalDate.parse(a)
        }
    }

    fun saveReturnDate(date: LocalDate?) {
        runBlocking {
            preferences.edit {
                if (date == null) {
                    it[DataStorePrefencesKeys.RETURN_DATE_KEY] = "null"
                    return@edit
                }
                it[DataStorePrefencesKeys.RETURN_DATE_KEY] = date.toString()
            }
        }
    }
}