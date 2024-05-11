package ru.alexereh.tickets.presentation.search.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.databinding.CardDirectionBinding
import ru.alexereh.tickets.presentation.search.model.DirectionModel

class DirectionAdapter(val selectedCity: (String) -> Unit) :
    RecyclerView.Adapter<DirectionViewHolder>() {
    val data = listOf(
        DirectionModel(id = 1, town = "Стамбул"),
        DirectionModel(id = 2, town = "Сочи"),
        DirectionModel(id = 3, town = "Пхукет"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionViewHolder {
        val binding =
            CardDirectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        with(binding) {
            root.isClickable = true
            root.setOnClickListener {
                selectedCity(directionTownTv.text.toString())
            }
        }
        return DirectionViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        holder.bind(data[position])
    }
}