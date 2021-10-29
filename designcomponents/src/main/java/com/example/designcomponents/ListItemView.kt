package com.example.designcomponents

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.IntDef
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.designcomponents.ListItemView.ActionType.Companion.CHEVRON
import com.example.designcomponents.ListItemView.ActionType.Companion.NONE
import com.example.designcomponents.ListItemView.ActionType.Companion.TOGGLE_SWITCH
import com.example.designcomponents.databinding.ListItemBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class ListItemView @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var binding: ListItemBinding
    @ActionType
    private var viewType: Int = 0
    private var genericClickListener: OnClickListener? = null
    private var compoundClickListener: OnUserCheckChangedListener? = null

    private var switch: SwitchMaterial? = null

    companion object {
        val className = ListItemView::class.java.simpleName
    }

    init {
        setupBinding()
        attrs?.let { setUpAttributes(it) }
        setupClickListeners()
    }

    protected open fun setupBinding() {
        binding = ListItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    protected open fun setUpAttributes(attributes: AttributeSet) {
        context.getStyledAttributes(attributes, R.styleable.ListItemView) {
            setIconStart(getString(R.styleable.ListItemView_iconStart))
            setLabelText(getString(R.styleable.ListItemView_labelText))
            setActionEnd(getInt(R.styleable.ListItemView_actionEnd, CHEVRON))
        }
    }

    private fun setupClickListeners() {
        getClickable().setOnClickListener {
            when (viewType) {
                NONE, CHEVRON -> {
                    genericClickListener?.onClick(it) ?: Log.d(
                        className,
                        "Tried to invoke click listener, but listener not set."
                    )
                }
                TOGGLE_SWITCH -> {
                    switch?.let { switch ->
                        switch.isChecked = !switch.isChecked
                        compoundClickListener?.onCheckChanged(switch.isChecked) ?: Log.d(
                            className, "Tried to invoke click listener, but listener not set."
                        )
                    } ?: Log.d(
                        className,
                        "Attempted to invoke click listener on a view that does not support it"
                    )
                }
            }
        }
    }

    fun setIconStart(icon: String?) {
        with(getIconRef()) {
            icon?.let {
                Glide.with(context).load(it).into(binding.iconProfile)
                isVisible = true
            } ?: run { isVisible = false }
        }
    }

    fun setLabelText(labelText: String?) {
        with(getLabelRef()) {
            text = labelText ?: resources.getString(R.string.default_text_label)
        }
    }

    fun setActionEnd(@ActionType action: Int) {
        with(getRightContentFrame()) {
            isVisible = action != NONE
            switch = null
            removeAllViews()

            val viewToAdd = when (action) {
                CHEVRON -> ImageView(context).apply {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_arrow_right
                        )
                    )
                }
                TOGGLE_SWITCH -> {
                    switch = SwitchMaterial(context, attrs)
                    switch
                }
                else -> null
            }
            viewToAdd?.let { addView(it) }
        }
        viewType = action
    }

    protected open fun getIconRef() = binding.iconProfile

    protected open fun getLabelRef() = binding.labelText

    protected open fun getRightContentFrame() = binding.rightContent

    protected open fun getClickable(): View = binding.clickable


    @Retention(AnnotationRetention.SOURCE)
    @IntDef(NONE, CHEVRON, TOGGLE_SWITCH)
    annotation class ActionType {
        companion object {
            const val NONE = 0
            const val CHEVRON = 1
            const val TOGGLE_SWITCH = 2
        }
    }

    fun setClickListener(l: OnClickListener?) {
        this.genericClickListener = l
    }

    fun setOnCheckChangedListener(l: OnUserCheckChangedListener) {
        this.compoundClickListener = l
    }


    interface OnUserCheckChangedListener {
        fun onCheckChanged(isChecked: Boolean)
    }
}