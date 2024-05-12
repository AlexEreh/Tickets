package ru.alexereh.tickets.presentation.selectedsearch.recycler.ticketsoffers

import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class TicketsOffersItemDecoration @Inject constructor() : RecyclerView.ItemDecoration() {
    private val metrics: DisplayMetrics

    init {
        metrics = Resources.getSystem().displayMetrics
    }

    private val SPACING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, metrics)
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            // Первый
            0 -> {
            }
            // Иной
            else -> {
                outRect.top = SPACING.roundToInt()
            }
        }
    }
}