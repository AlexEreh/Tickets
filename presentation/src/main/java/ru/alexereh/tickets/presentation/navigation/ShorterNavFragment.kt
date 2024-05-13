package ru.alexereh.tickets.presentation.navigation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.presentation.R
import javax.inject.Inject

@AndroidEntryPoint
class ShorterNavFragment @Inject constructor(): Fragment(R.layout.fragment_nav_shorter)