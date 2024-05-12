package ru.alexereh.tickets.presentation.search.fragment

import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.alexereh.tickets.R
import ru.alexereh.tickets.databinding.FragmentSearchBinding
import ru.alexereh.tickets.presentation.search.recycler.DirectionAdapter
import ru.alexereh.tickets.presentation.search.recycler.DirectionItemDecoration
import ru.alexereh.tickets.presentation.search.viewmodel.SearchViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class SearchFragment @Inject constructor(): BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private fun createMaterialShapeDrawable(bottomSheet: View): MaterialShapeDrawable {
        val shapeAppearanceModel =
            ShapeAppearanceModel
                .builder(context, 0, R.style.CustomShapeAppearanceBottomSheetDialog)
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
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog =
            BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialog)
        // Плотность понадобится нам в дальнейшем
        val density = requireContext().resources.displayMetrics.density
        bottomSheetDialog.let { dialog ->
            dialog.setOnShowListener {
                val bottomSheet =
                    dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
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
                behavior.maxHeight =
                    (displayMetrics.heightPixels * displayMetrics.density).roundToInt()
                behavior.peekHeight =
                    (displayMetrics.heightPixels * displayMetrics.density).roundToInt()
                val bg = createMaterialShapeDrawable(bottomSheet)
                bottomSheet.setBackground(bg.current);
                bottomSheetDialog.dismissWithAnimation = false
                behavior.isFitToContents = false
                behavior.isDraggable = false
                behavior.isHideable = false
            }

        }
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        with(binding) {
            crossIv.setOnClickListener {
                lowerEt.text.clear()
            }
            directionsRv.adapter = DirectionAdapter(selectedCity = {
                lowerEt.text = SpannableStringBuilder(it)
                lowerEt.setSelection(it.length, it.length)
            })
            anywhereAction.setOnClickListener {
                val text = getString(R.string.text_anywhere)
                lowerEt.text = SpannableStringBuilder(text)
                lowerEt.setSelection(text.length, text.length)
            }
            directionsRv.layoutManager = object : LinearLayoutManager(
                this@SearchFragment.requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            ) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            val navController = requireActivity().findNavController(R.id.nav_fragment_view)
            requireActivity().onBackPressedDispatcher.addCallback(this@SearchFragment) {
                navController.popBackStack()
            }
            directionsRv.addItemDecoration(DirectionItemDecoration())
            difficultRouteAction.setOnClickListener {
                navController.navigate(R.id.action_searchFragment_to_stubReturnNavFragment)
            }
            holidaysAction.setOnClickListener {
                navController.navigate(R.id.action_searchFragment_to_stubReturnNavFragment)
            }
            hotTickets.setOnClickListener {
                navController.navigate(R.id.action_searchFragment_to_stubReturnNavFragment)
            }

            lowerEt.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.saveSecondSearch(lowerEt.text.toString())
                    navController.navigate(R.id.action_searchFragment_to_selectedSearchFragment)
                    return@setOnEditorActionListener true
                }
                false
            }
        }
        return binding.root
    }
}