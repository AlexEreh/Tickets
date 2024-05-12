package ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.databinding.CardTicketOfferBinding
import ru.alexereh.tickets.domain.model.TicketsOfferModel
import ru.alexereh.tickets.domain.model.TicketsOffersModel

class TicketsOffersAdapter : RecyclerView.Adapter<TicketsOffersViewHolder>() {
    private val data = mutableListOf<TicketsOfferModel>()
    fun updateData(newData: TicketsOffersModel) {
        data.clear()
        data.addAll(newData.ticketsOffers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsOffersViewHolder {
        return TicketsOffersViewHolder(
            CardTicketOfferBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TicketsOffersViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}