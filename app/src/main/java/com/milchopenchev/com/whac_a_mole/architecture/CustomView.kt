package com.milchopenchev.com.whac_a_mole.architecture

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

abstract class CustomView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    init {
        setWillNotDraw(false)
    }

    private var _rect = Rect(0, 0, 0, 0)
    protected val rect: Rect
        get() = _rect

    override fun onLayout(changed: Boolean, _left: Int, _top: Int, _right: Int, _bottom: Int) {
        super.onLayout(changed, _left, _top, _right, _bottom)
        _rect.run {
            left = _left
            top = _top
            right = _right
            bottom = _bottom
        }
    }
}