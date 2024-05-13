package ru.alexereh.tickets.data.local

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStorePrefencesKeys {
    val FIRST_SEARCH_KEY = stringPreferencesKey("first_search")
    val SECOND_SEARCH_KEY = stringPreferencesKey("second_search")
}