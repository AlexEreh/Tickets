package ru.alexereh.tickets.presentation.alltickets.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.alltickets.recycler.AllTicketsAdapter
import ru.alexereh.tickets.presentation.alltickets.recycler.TicketItemDecoration
import ru.alexereh.tickets.presentation.alltickets.viewmodel.AllTicketsViewModel
import ru.alexereh.tickets.presentation.databinding.FragmentAllTicketsBinding
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AllTicketsFragment @Inject constructor() : Fragment() {
    lateinit var binding: FragmentAllTicketsBinding
    lateinit var viewModel: AllTicketsViewModel

    @Inject
    lateinit var adapter: AllTicketsAdapter

    @Inject
    lateinit var itemDecoration: TicketItemDecoration
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(AllTicketsViewModel::class.java)
        binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        with(binding) {
            ticketsRv.adapter = adapter
            ticketsRv.addItemDecoration(itemDecoration)
            ticketsRv.layoutManager =
                LinearLayoutManager(ticketsRv.context, LinearLayoutManager.VERTICAL, false)
            lifecycleScope.launch {
                viewModel.tickets
                    .onEach {
                        adapter.updateData(it)
                    }
                    .collect()
            }
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        with(binding) {
            lifecycleScope.launch {
                viewModel.destinations
                    .onEach {
                        townsTv.text = it
                    }
                    .collect()
            }
            lifecycleScope.launch {
                viewModel.departureDate
                    .onEach {
                        statusTv.text = "${it.dayOfMonth} ${
                            it.month.getDisplayName(
                                TextStyle.FULL,
                                Locale("ru", "RU")
                            )
                        }, 1 пассажир"
                    }
                    .collect()
            }
            val navController = requireActivity().findNavController(R.id.nav_fragment_view)
            backIv.setOnClickListener {
                navController.popBackStack()
            }
            requireActivity().onBackPressedDispatcher.addCallback(this@AllTicketsFragment) {
                navController.popBackStack()
            }
        }
    }
}