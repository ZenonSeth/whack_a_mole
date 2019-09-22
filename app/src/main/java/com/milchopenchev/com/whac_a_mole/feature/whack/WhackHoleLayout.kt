package com.milchopenchev.com.whac_a_mole.feature.whack

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.milchopenchev.com.whac_a_mole.R

class WhackHoleLayout
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    init {
        inflate(context, R.layout.hole_layout, this)
    }

}