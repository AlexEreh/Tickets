package ru.alexereh.tickets.presentation.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.FragmentStubPreviousBinding
import javax.inject.Inject

@AndroidEntryPoint
class StubReturnNavFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentStubPreviousBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStubPreviousBinding.inflate(inflater, container, false)
        with(binding) {
            val navController = requireActivity().findNavController(R.id.nav_fragment_view)
            binding.returnBtn.setOnClickListener {
                navController.apply {
                    navigate(R.id.action_stubReturnNavFragment_to_ticketsFragment)
                    navigate(R.id.action_ticketsFragment_to_searchFragment)
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(this@StubReturnNavFragment) {
                navController.apply {
                    navigate(R.id.action_stubReturnNavFragment_to_ticketsFragment)
                    navigate(R.id.action_ticketsFragment_to_searchFragment)
                }
            }
        }
        return binding.root
    }
}