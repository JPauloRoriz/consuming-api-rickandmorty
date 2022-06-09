package com.joaop.rickandmorty.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joaop.rickandmorty.R
import com.joaop.rickandmorty.databinding.CustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: CustomToolbarBinding by lazy {
        CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    }

    var title: String
        get() = binding.tvTitle.text.toString()
        set(value) {
            binding.tvTitle.text = value
        }

    var subtitle: String
        get() = binding.tvSubtitle.text.toString()
        set(value) {
            binding.tvSubtitle.text = value
        }

    var colorText: Int? = null
        set(value) {
            field = value
            value?.let {
                binding.tvTitle.setTextColor(value)
                binding.tvSubtitle.setTextColor(value)
            }
        }

    init {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar, defStyleAttr, 0)
        title = attributes.getString(R.styleable.CustomToolbar_title) ?: ""
        subtitle = attributes.getString(R.styleable.CustomToolbar_subtitle) ?: ""
        colorText = attributes.getColor(R.styleable.CustomToolbar_colorText, 0)
    }


}
