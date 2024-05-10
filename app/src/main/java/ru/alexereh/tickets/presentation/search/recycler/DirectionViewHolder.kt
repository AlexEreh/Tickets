package ru.alexereh.tickets.presentation.search.recycler

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.CardDirectionBinding
import ru.alexereh.tickets.presentation.search.model.DirectionModel

class DirectionViewHolder(
    private val binding: CardDirectionBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: DirectionModel) {
        binding.directionTownTv.text = model.town
        when (model.id) {
            1 -> {
                binding.directionIv.setImageDrawable(
                    AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.img_first_direction
                    )
                )
            }

            2 -> {
                binding.directionIv.setImageDrawable(
                    AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.img_second_direction
                    )
                )
            }

            3 -> {
                binding.directionIv.setImageDrawable(
                    AppCompatResources.getDrawable(
                        binding.root.context,
                        R.drawable.img_third_direction
                    )
                )
            }
        }
    }
}