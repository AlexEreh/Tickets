package ru.alexereh.tickets.navigation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.R
import javax.inject.Inject

@AndroidEntryPoint
class ProfileNavFragment @Inject constructor(): Fragment(R.layout.fragment_nav_profile)