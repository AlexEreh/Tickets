package ru.alexereh.tickets.presentation.selectedsearch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.FragmentSelectedSearchBinding
import javax.inject.Inject

class SelectedSearchFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentSelectedSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectedSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val navController = requireActivity().findNavController(R.id.nav_fragment_view)
        requireActivity().onBackPressedDispatcher.addCallback(this@SelectedSearchFragment) {
            navController.apply {
                navigate(R.id.action_selectedSearchFragment_to_ticketsFragment)
                navigate(R.id.action_ticketsFragment_to_searchFragment)
            }
        }
        binding.backIv.setOnClickListener {
            navController.apply {
                navigate(R.id.action_selectedSearchFragment_to_ticketsFragment)
                navigate(R.id.action_ticketsFragment_to_searchFragment)
            }
        }
    }
}