package ru.alexereh.tickets.presentation.tickets.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.CardMusicalFlightBinding
import ru.alexereh.tickets.domain.model.OfferModel
import ru.alexereh.tickets.domain.model.OffersModel
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfferAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data: MutableList<OfferModel> = mutableListOf()

    class OfferViewHolder(
        val binding: CardMusicalFlightBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private fun formatter(n: Int) =
            DecimalFormat("#,###")
                .format(n)
                .replace(",", " ")
        @SuppressLint("SetTextI18n")
        fun bind(model: OfferModel) {
            binding.bandTv.text = model.title
            binding.cityTv.text = model.town
            binding.priceTv.text = "от ${formatter(model.price.value)} ₽"
            when (model.id) {
                1 -> {
                    binding.flightIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.img_first
                        )
                    )
                }

                2 -> {
                    binding.flightIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.img_second
                        )
                    )
                }

                3 -> {
                    binding.flightIv.setImageDrawable(
                        AppCompatResources.getDrawable(
                            binding.root.context,
                            R.drawable.img_third
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CardMusicalFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OfferViewHolder) {
            holder.bind(data[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: OffersModel) {
        this.data.clear()
        this.data.addAll(data.offers)
        notifyDataSetChanged()
    }
}