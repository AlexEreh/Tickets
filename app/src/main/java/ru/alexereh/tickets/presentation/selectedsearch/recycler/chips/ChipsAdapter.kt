package ru.alexereh.tickets.presentation.selectedsearch.recycler.chips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.databinding.ChipClassBinding
import ru.alexereh.tickets.databinding.ChipDateBinding
import ru.alexereh.tickets.databinding.ChipFilterBinding
import ru.alexereh.tickets.databinding.ChipReturnBinding

class ChipsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderType.RETURN_CHIP.type -> {
                ReturnChipViewHolder(
                    ChipReturnBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            ViewHolderType.DATE_CHIP.type -> {
                DateChipViewHolder(
                    ChipDateBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            ViewHolderType.CLASS_CHIP.type -> {
                ClassChipViewHolder(
                    ChipClassBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            ViewHolderType.FILTER_CHIP.type -> {
                FilterViewHolder(
                    ChipFilterBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    enum class ViewHolderType(val type: Int) {
        RETURN_CHIP(1), DATE_CHIP(2), CLASS_CHIP(3), FILTER_CHIP(4)
    }
}