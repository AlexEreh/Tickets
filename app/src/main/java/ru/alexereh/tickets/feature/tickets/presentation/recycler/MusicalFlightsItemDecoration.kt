package ru.alexereh.tickets.feature.tickets.presentation.recycler

import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import kotlin.math.roundToInt


class MusicalFlightsItemDecoration @Inject constructor() : RecyclerView.ItemDecoration(){
    private val metrics: DisplayMetrics
    init {
        metrics = Resources.getSystem().displayMetrics
    }
    private val SPACING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 67f, metrics)
    private val START_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    private val END_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when(parent.getChildAdapterPosition(view)) {
            // Первый
            0 -> {
                outRect.left = START_PADDING.roundToInt()
                outRect.right = SPACING.roundToInt()
            }
            // Последний
            parent.adapter!!.getItemCount() - 1 -> {
                outRect.right = END_PADDING.roundToInt()

            }
            // Иной
            else -> {
                outRect.right = SPACING.roundToInt()
            }
        }
    }
    companion object {

    }
}