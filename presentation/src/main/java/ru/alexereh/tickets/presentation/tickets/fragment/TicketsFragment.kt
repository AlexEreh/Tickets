package ru.alexereh.tickets.presentation.tickets.fragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.FragmentTicketsBinding
import ru.alexereh.tickets.presentation.tickets.recycler.OfferAdapter
import ru.alexereh.tickets.presentation.tickets.recycler.OfferItemDecoration
import ru.alexereh.tickets.presentation.tickets.viewmodel.TicketsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TicketsFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentTicketsBinding
    private lateinit var viewModel: TicketsViewModel

    @Inject
    lateinit var offerAdapter: OfferAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(TicketsViewModel::class.java)
        binding = FragmentTicketsBinding.inflate(inflater, container, false)
        with(binding) {
            offerRv.adapter = offerAdapter
            offerRv.addItemDecoration(OfferItemDecoration())
            offerRv.layoutManager = LinearLayoutManager(
                offerRv.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            lowerEt.isFocusable = false
            lowerEt.setOnClickListener { v ->
                viewModel.onFirstSearch(upperEt.text.toString())
                Navigation.findNavController(this@TicketsFragment.requireView())
                    .navigate(R.id.action_ticketsFragment_to_searchFragment)
                v.clearFocus()
                upperEt.requestFocus()
            }
            lifecycleScope.launch {
                viewModel.offers
                    .onEach {
                        offerAdapter.updateData(it)
                    }
                    .catch {
                        Toast.makeText(this@TicketsFragment.context, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    .collect()
            }
            lifecycleScope.launch {
                viewModel.firstSearch
                    .onEach {
                        upperEt.text = SpannableStringBuilder(it)
                        upperEt.setSelection(it.length, it.length)
                    }
                    .catch {
                        Toast.makeText(this@TicketsFragment.context, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    .collect()
            }
            upperEt.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    viewModel.onFirstSearch(upperEt.text.toString())
                    Navigation.findNavController(this@TicketsFragment.requireView())
                        .navigate(R.id.action_ticketsFragment_to_searchFragment)
                }
                true
            }
        }
        return binding.root
    }
}