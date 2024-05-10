package ru.alexereh.tickets.presentation.search.fragment

import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexereh.tickets.R
import ru.alexereh.tickets.R.style
import ru.alexereh.tickets.databinding.FragmentSearchBinding
import ru.alexereh.tickets.presentation.search.recycler.DirectionAdapter
import ru.alexereh.tickets.presentation.search.recycler.DirectionItemDecoration
import ru.alexereh.tickets.presentation.search.viewmodel.SearchViewModel
import javax.inject.Inject
import kotlin.math.roundToInt


class SearchFragment @Inject constructor(): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private val directionAdapter = DirectionAdapter()
    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel
                .builder(context, 0, style.CustomShapeAppearanceBottomSheetDialog)
                .build()
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

    override fun onStart() {
        super.onStart()
        with(binding) {
            lifecycleScope.launch {
                viewModel.firstSearch
                    .onEach {
                        upperEt.text = SpannableStringBuilder(it)
                    }
                    .collect()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.lowerEt.requestFocus()
        val imm = getSystemService(binding.root.context, InputMethodManager::class.java)
        imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireActivity(), style.CustomBottomSheetDialog)
        binding = FragmentSearchBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
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
            bottomSheetDialog.dismissWithAnimation = false
            behavior.isFitToContents = false
            behavior.isDraggable = false
            behavior.isHideable = false
        }
        with(binding) {
            crossIv.setOnClickListener {
                lowerEt.text.clear()
            }
            directionsRv.adapter = directionAdapter
            directionsRv.layoutManager = object : LinearLayoutManager(
                this@SearchFragment.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            ) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            directionsRv.addItemDecoration(DirectionItemDecoration())
        }
        return bottomSheetDialog

    }
}