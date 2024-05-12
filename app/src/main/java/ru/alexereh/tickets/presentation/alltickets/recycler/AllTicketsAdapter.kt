package ru.alexereh.tickets.presentation.alltickets.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.databinding.CardTicketBinding
import ru.alexereh.tickets.domain.model.TicketModel
import ru.alexereh.tickets.domain.model.TicketsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllTicketsAdapter @Inject constructor() : RecyclerView.Adapter<TicketViewHolder>() {
    private val data = mutableListOf<TicketModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            CardTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun updateData(newData: TicketsModel) {
        data.clear()
        data.addAll(newData.tickets)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(data[position])
    }
}