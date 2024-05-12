package ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.CardTicketOfferBinding
import ru.alexereh.tickets.domain.model.TicketsOfferModel

class TicketsOffersViewHolder(
    private val binding: CardTicketOfferBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: TicketsOfferModel, position: Int) {
        with(binding) {
            timeRangeTv.text = model.timeRange.reduce { accumulator, current ->
                accumulator + " " + current
            }
            titleTv.text = model.title
            when (position) {
                0 -> {
                    directionIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.bg_red_circle_ticket
                        )
                    )
                }

                1 -> {
                    directionIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.bg_blue_circle_ticket
                        )
                    )
                }

                2 -> {
                    directionIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.bg_white_circle_ticket
                        )
                    )
                }
            }
        }
    }
}