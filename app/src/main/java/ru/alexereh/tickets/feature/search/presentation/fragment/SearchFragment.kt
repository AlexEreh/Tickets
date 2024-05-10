package ru.alexereh.tickets.feature.search.presentation.fragment

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowMetrics
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import ru.alexereh.tickets.R
import ru.alexereh.tickets.R.*
import ru.alexereh.tickets.databinding.FragmentSearchBinding
import javax.inject.Inject
import kotlin.math.roundToInt


class SearchFragment @Inject constructor(): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding

    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel
                .builder(context, 0, style.CustomShapeAppearanceBottomSheetDialog)
                .build()
        Log.d("LOL", "createMaterialShapeDrawable: ${bottomSheet}, ${bottomSheet.background}")
        val currentMaterialShapeDrawable = bottomSheet.background as? MaterialShapeDrawable
        val newMaterialShapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)

        newMaterialShapeDrawable.initializeElevationOverlay(context)
        newMaterialShapeDrawable.fillColor = currentMaterialShapeDrawable?.fillColor
        newMaterialShapeDrawable.tintList = currentMaterialShapeDrawable?.tintList
        newMaterialShapeDrawable.elevation = currentMaterialShapeDrawable?.elevation ?: 0f
        newMaterialShapeDrawable.strokeWidth = 0f
        newMaterialShapeDrawable.strokeColor = ColorStateList.valueOf(getResources().getColor(R.color.black))
        return newMaterialShapeDrawable
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireActivity(), style.CustomBottomSheetDialog)
        binding = FragmentSearchBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        // Плотность понадобится нам в дальнейшем
        val density = requireContext().resources.displayMetrics.density
        bottomSheetDialog.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            val displayMetrics = DisplayMetrics()
            requireActivity().getWindowManager().defaultDisplay.getMetrics(displayMetrics)
            behavior.expandedOffset = (density * 23).roundToInt()
            val layoutParams = bottomSheet.layoutParams?.apply {
                height = (displayMetrics.heightPixels * displayMetrics.density).roundToInt()
                //behavior.maxHeight = height
            }
            bottomSheet.layoutParams = layoutParams;
            behavior.halfExpandedRatio = 0.99f
            behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            //behavior.maxHeight = bottomSheetHeight
            //behavior.peekHeight = bottomSheetHeight
            val bg = createMaterialShapeDrawable(bottomSheet)
            bottomSheet.setBackground(bg.current);
            behavior.isFitToContents = false

            behavior.isDraggable = false
            behavior.isHideable = false
        }
        return bottomSheetDialog

    }
}