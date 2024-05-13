package ru.alexereh.tickets.presentation.selectedsearch.recycler.chips

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
class ChipsItemDecoration @Inject constructor() : RecyclerView.ItemDecoration() {
    private val metrics: DisplayMetrics

    init {
        metrics = Resources.getSystem().displayMetrics
    }

    private val SPACING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, metrics)
    private val START_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    private val END_PADDING = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, metrics)
    private val PRE_FILTER_SPACE =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 21f, metrics)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            // Первый
            0 -> {
                outRect.left = START_PADDING.roundToInt()
            }
            // Последний
            parent.adapter!!.itemCount - 1 -> {
                outRect.left = PRE_FILTER_SPACE.roundToInt()
                outRect.right = END_PADDING.roundToInt()
            }
            // Иной
            else -> {
                outRect.left = SPACING.roundToInt()
            }
        }
    }
}