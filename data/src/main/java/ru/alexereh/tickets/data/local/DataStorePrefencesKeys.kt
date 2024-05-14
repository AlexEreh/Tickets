package ru.alexereh.tickets.data.local

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStorePrefencesKeys {
    val FIRST_SEARCH_KEY = stringPreferencesKey("first_search")
    val SECOND_SEARCH_KEY = stringPreferencesKey("second_search")
    val DEPARTURE_DATE_KEY = stringPreferencesKey("departure_date")
    val RETURN_DATE_KEY = stringPreferencesKey("return_date")
}