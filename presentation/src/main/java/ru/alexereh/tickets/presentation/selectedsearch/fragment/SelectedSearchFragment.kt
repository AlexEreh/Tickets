package ru.alexereh.tickets.presentation.selectedsearch.fragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.FragmentSelectedSearchBinding
import ru.alexereh.tickets.presentation.selectedsearch.recycler.chips.ChipsAdapter
import ru.alexereh.tickets.presentation.selectedsearch.recycler.chips.ChipsItemDecoration
import ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers.TicketsOffersAdapter
import ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers.TicketsOffersItemDecoration
import ru.alexereh.tickets.presentation.selectedsearch.viewmodel.SelectedSearchViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SelectedSearchFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentSelectedSearchBinding
    private lateinit var viewModel: SelectedSearchViewModel
    private lateinit var adapter: TicketsOffersAdapter

    @Inject
    lateinit var chipsItemDecoration: ChipsItemDecoration
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(SelectedSearchViewModel::class.java)
        binding = FragmentSelectedSearchBinding.inflate(inflater, container, false)
        with(binding) {
            chipsRv.adapter = ChipsAdapter()
            chipsRv.layoutManager =
                LinearLayoutManager(chipsRv.context, LinearLayoutManager.HORIZONTAL, false)
            chipsRv.addItemDecoration(chipsItemDecoration)
            swapIv.setOnClickListener {
                viewModel.swapDepratureArrivalTowns()
            }
            adapter = TicketsOffersAdapter()
            ticketsOffersRv.adapter = adapter
            ticketsOffersRv.layoutManager =
                LinearLayoutManager(ticketsOffersRv.context, LinearLayoutManager.VERTICAL, false)
            ticketsOffersRv.addItemDecoration(TicketsOffersItemDecoration())
            lifecycleScope.launch {
                viewModel.secondSearch
                    .onEach {
                        lowerEt.text = SpannableStringBuilder(it)
                        lowerEt.setSelection(it.length, it.length)
                        lowerEt.clearFocus()
                    }
                    .catch {
                        Toast.makeText(
                            this@SelectedSearchFragment.context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect()
            }
            lifecycleScope.launch {
                viewModel.firstSearch
                    .onEach {
                        upperEt.text = SpannableStringBuilder(it)
                        upperEt.setSelection(it.length, it.length)
                        upperEt.clearFocus()
                    }
                    .catch {
                        Toast.makeText(
                            this@SelectedSearchFragment.context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect()
            }
            lifecycleScope.launch {
                viewModel.ticketsOffers
                    .onEach {
                        adapter.updateData(it)
                    }
                    .catch {
                        Toast.makeText(
                            this@SelectedSearchFragment.context,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .collect()
            }
        }
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
        binding.crossIv.setOnClickListener {
            viewModel.clearArrivalTown()
            with(navController) {
                navigate(R.id.action_selectedSearchFragment_to_ticketsFragment)
                navigate(R.id.action_ticketsFragment_to_searchFragment)
            }
        }
        binding.showAllTicketsBtn.setOnClickListener {
            navController.navigate(R.id.action_selectedSearchFragment_to_allTicketsFragment)
        }
    }
}