package com.milchopenchev.com.whac_a_mole.feature.whack

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.milchopenchev.com.whac_a_mole.R
import kotlinx.android.synthetic.main.hole_layout.view.*

class WhackHoleLayout
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val animator = AnimatorInflater.loadAnimator(context, R.animator.peek_up_normal)

    private var onMoleClicked: () -> Unit = {}

    init {
        inflate(context, R.layout.hole_layout, this)
        animator.setTarget(this.the_mole)
        this.the_mole.setOnClickListener { onMoleClicked() }
    }


    fun popUp() {
        animator.start()
    }

    fun setOnMoleClickedListener(listener: (Int) -> Unit, id: Int) {
        onMoleClicked = { listener(id) }
    }
}