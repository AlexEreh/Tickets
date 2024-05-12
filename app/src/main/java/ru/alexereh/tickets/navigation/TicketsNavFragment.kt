package ru.alexereh.tickets.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.databinding.FragmentNavTicketsBinding
import javax.inject.Inject

@AndroidEntryPoint
class TicketsNavFragment @Inject constructor() : NavHostFragment() {
    private lateinit var binding: FragmentNavTicketsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }
}