package ru.alexereh.tickets.presentation.selectedsearch.recycler.chips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexereh.tickets.presentation.R
import ru.alexereh.tickets.presentation.databinding.ChipClassBinding
import ru.alexereh.tickets.presentation.databinding.ChipDateBinding
import ru.alexereh.tickets.presentation.databinding.ChipFilterBinding
import ru.alexereh.tickets.presentation.databinding.ChipReturnBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.Locale

class ChipsAdapter(
    private val onDepartureDateClicked: () -> Unit,
    private val onReturnDateClicked: () -> Unit,
    private val scope: CoroutineScope,
    private val departureDateFlow: Flow<LocalDate>,
    private val returnDateFlow: Flow<LocalDate?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val locale = Locale("ru", "RU")

    private val formatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.MEDIUM)
        .withLocale(locale)

    private fun dateToUiString(date: LocalDate): String {
        val input = date.format(formatter)
        val parts = input.split(" ")
        val result = "${parts[0]} ${parts[1].replace(".", "")}, " +
                "${date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)}"
        return result
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderType.RETURN_CHIP.type -> {
                ReturnChipViewHolder(
                    ChipReturnBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                ).apply {
                    binding.root.visibility = View.GONE
                    onReturnViewHolderCreated(binding)
                }
            }

            ViewHolderType.DATE_CHIP.type -> {
                DateChipViewHolder(
                    ChipDateBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                ).apply {
                    binding.root.visibility = View.GONE
                    onDateViewHolderCreated(binding)
                }
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

    private fun onDateViewHolderCreated(binding: ChipDateBinding) {
        binding.root.setOnClickListener {
            onDepartureDateClicked()
        }
        scope.launch(Dispatchers.IO) {
            departureDateFlow
                .onEach {
                    withContext(Dispatchers.Main.immediate) {
                        binding.root.text = dateToUiString(it)
                        binding.root.visibility = View.VISIBLE
                    }
                }
                .collect()
        }
    }

    private fun onReturnViewHolderCreated(binding: ChipReturnBinding) {
        binding.root.setOnClickListener {
            onReturnDateClicked()
        }
        scope.launch(Dispatchers.IO) {
            returnDateFlow
                .onEach {
                    withContext(Dispatchers.Main.immediate) {
                        updateReturnView(binding, it)
                    }
                }
                .collect()
        }
    }

    private fun updateReturnView(binding: ChipReturnBinding, date: LocalDate?) {
        when (date) {
            null -> {
                binding.root.chipIcon =
                    AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_plus)
                binding.root.text = "обратно"
            }

            else -> {
                binding.root.chipIcon = null
                binding.root.text = dateToUiString(date)
            }
        }
        binding.root.visibility = View.VISIBLE
    }

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    enum class ViewHolderType(val type: Int) {
        RETURN_CHIP(1), DATE_CHIP(2), CLASS_CHIP(3), FILTER_CHIP(4)
    }
}