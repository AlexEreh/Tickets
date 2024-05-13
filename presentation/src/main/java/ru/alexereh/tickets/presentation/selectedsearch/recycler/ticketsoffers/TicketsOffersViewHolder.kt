package ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers

import android.annotation.SuppressLint
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.domain.model.TicketsOfferModel
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.CardTicketOfferBinding
import java.text.DecimalFormat

class TicketsOffersViewHolder(
    private val binding: CardTicketOfferBinding
) : RecyclerView.ViewHolder(binding.root) {
    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", " ")

    @SuppressLint("SetTextI18n")
    fun bind(model: TicketsOfferModel, position: Int) {
        with(binding) {
            timeRangeTv.text = model.timeRange.reduce { accumulator, current ->
                accumulator + " " + current
            }
            titleTv.text = model.title
            priceTv.text = formatter(model.price.value) + " ₽"
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