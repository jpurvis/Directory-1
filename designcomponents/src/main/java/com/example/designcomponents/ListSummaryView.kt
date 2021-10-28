package com.example.designcomponents

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.designcomponents.databinding.ItemListSummaryBinding


open class ListSummaryView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ItemListSummaryBinding

    init {
        initElements(context, attrs)
    }


    open fun initElements(context: Context, attrs: AttributeSet?) {
        binding = ItemListSummaryBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let { attributes ->
            context.getStyledAttributes(attributes, R.styleable.ListSummaryView) {
                setLabelStartText(getString(R.styleable.ListSummaryView_labelStartText))
                setLabelEndText(getString(R.styleable.ListSummaryView_labelEndText))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setLabelBoldStyleEnabled(
                        getBoolean(
                            R.styleable.ListSummaryView_labelBoldStyleEnabled,
                            false
                        )
                    )
                }
            }
        }
    }

    open fun getStartTextView() = binding.labelStartText

    open fun getEndTextView() = binding.labelEndText

    open fun setLabelStartText(labelStartText: String?) {
        getStartTextView().apply {
            text = labelStartText ?: resources.getString(R.string.default_text_label)
        }
    }

    open fun setLabelEndText(labelEndText: String?) {
        getEndTextView().apply {
            text = labelEndText ?: resources.getString(R.string.default_text_paragraph)
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    open fun setLabelBoldStyleEnabled(enabled: Boolean) {
        if (enabled) {
            getStartTextView().setTextAppearance(R.style.ListSummary_Bold)
                getEndTextView().setTextAppearance(R.style.ListSummary_Bold)
        } else {
            setNormalTextAppearance()
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    open fun setNormalTextAppearance() {
        getStartTextView().setTextAppearance(R.style.ListSummary_Normal_Left)
        getEndTextView().setTextAppearance(R.style.ListSummary_Normal_Right)
    }
}