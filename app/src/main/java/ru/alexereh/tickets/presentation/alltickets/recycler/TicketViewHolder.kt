package ru.alexereh.tickets.presentation.alltickets.recycler

import android.view.View.VISIBLE
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.databinding.CardTicketBinding
import ru.alexereh.tickets.domain.model.TicketModel
import java.text.DecimalFormat
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class TicketViewHolder(private val binding: CardTicketBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var binded = false
    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", " ")

    fun bind(model: TicketModel) {
        if (binded) {
            return
        }
        binded = true
        binding.priceTv.text = "${formatter(model.price.value)} ₽"
        binding.departureAirportTv.text = model.departure.airport
        val departureDate = model.departure.date
        binding.departureTimeTv.text = "${departureDate.hour.toString().padStart(2, '0')}:${
            departureDate.minute.toString().padStart(2, '0')
        }"
        binding.arrivalAirportTv.text = model.arrival.airport
        val arrivalDate = model.arrival.date
        binding.arrivalTimeTv.text = "${arrivalDate.hour.toString().padStart(2, '0')}:${
            arrivalDate.minute.toString().padStart(2, '0')
        }"
        val params = binding.outerConstraint.layoutParams as ConstraintLayout.LayoutParams
        if (model.badge.isNotBlank()) {
            binding.badge.visibility = VISIBLE
            binding.additionalTextTv.text = model.badge
            params.setMargins(0, 20, 0, 0)
        } else {
            params.setMargins(0, 0, 0, 0)
        }
        val hours = abs(ChronoUnit.HOURS.between(model.departure.date, model.arrival.date))
        val minutes = abs(ChronoUnit.MINUTES.between(model.departure.date, model.arrival.date) % 60)
        binding.infoTv.text =
            "${hours}" + (if (minutes > 20 || minutes < 40) ".5" else "") + "ч в пути" + (if (!model.hasTransfer) "/Без пересадок" else "")
    }
}