package ru.alexereh.tickets.presentation.search.recycler

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
class DirectionItemDecoration @Inject constructor() : RecyclerView.ItemDecoration() {
    private val metrics: DisplayMetrics

    init {
        metrics = Resources.getSystem().displayMetrics
    }

    private val SPACING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, metrics)
    private val START_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    private val END_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            // Первый
            0 -> {
                outRect.top = START_PADDING.roundToInt()
                outRect.bottom = SPACING.roundToInt()
            }
            // Последний
            parent.adapter!!.getItemCount() - 1 -> {
                outRect.bottom = END_PADDING.roundToInt()

            }
            // Иной
            else -> {
                outRect.bottom = SPACING.roundToInt()
            }
        }
    }
}