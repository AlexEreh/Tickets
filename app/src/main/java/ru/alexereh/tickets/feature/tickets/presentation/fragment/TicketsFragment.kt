package ru.alexereh.tickets.feature.tickets.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.FragmentTicketsBinding
import ru.alexereh.tickets.feature.tickets.presentation.recycler.MusicalFlightsAdapter
import ru.alexereh.tickets.feature.tickets.presentation.recycler.MusicalFlightsItemDecoration
import ru.alexereh.tickets.feature.tickets.presentation.recycler.OfferModel
import ru.alexereh.tickets.feature.tickets.presentation.recycler.PriceModel
import ru.alexereh.tickets.feature.tickets.presentation.viewmodel.TicketsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TicketsFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentTicketsBinding
    @Inject
    lateinit var itemDecoration: MusicalFlightsItemDecoration
    private lateinit var viewModel: TicketsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = listOf(
            OfferModel(
                id = 1,
                title = "Die Antwoord",
                town = "Будапешт",
                price = PriceModel(5000)
            ),
            OfferModel(
                id = 2,
                title = "Socrat&Lera",
                town = "Санкт-Петербург",
                price = PriceModel(1999)
            ),
            OfferModel(
                id = 3,
                title = "Лампабикт",
                town = "Москва",
                price = PriceModel(2390)
            ),
        )
        viewModel = ViewModelProvider(requireActivity()).get(TicketsViewModel::class.java)
        binding = FragmentTicketsBinding.inflate(inflater, container, false)
        with(binding) {
            musicalRv.adapter = MusicalFlightsAdapter(data)
            musicalRv.addItemDecoration(itemDecoration)
            musicalRv.layoutManager = LinearLayoutManager(
                musicalRv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            lowerEt.isFocusable = false
            upperEt.addTextChangedListener {

            }
            upperEt.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    viewModel.onFirstSearch(v.text.toString())
                    Navigation.findNavController(this@TicketsFragment.requireView()).navigate(R.id.action_ticketsFragment_to_searchFragment)
                }
                true
            }
        }
        return binding.root
    }
}