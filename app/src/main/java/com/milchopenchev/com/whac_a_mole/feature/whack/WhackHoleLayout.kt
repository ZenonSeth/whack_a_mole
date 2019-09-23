package com.milchopenchev.com.whac_a_mole.feature.whack

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import com.milchopenchev.com.whac_a_mole.R
import kotlinx.android.synthetic.main.hole_layout.view.*

class WhackHoleLayout
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private enum class State {
        Idle, Peek, Hit
    }

    private var currentState = State.Idle

    private fun sync(block: () -> Unit) = synchronized(this) { block() }

    private val peekAnimator =
        AnimatorInflater.loadAnimator(context, R.animator.peek_up_normal).apply {
            doOnStart { sync { currentState = State.Peek } }
            doOnEnd { sync { currentState = State.Idle } }
        }

    private val hitAnimator =
        AnimatorInflater.loadAnimator(context, R.animator.hit_animator).apply {
            doOnStart { sync { currentState = State.Hit } }
            doOnEnd { sync { currentState = State.Idle } }
        }

    private var onMoleClicked: () -> Unit = {}

    init {
        inflate(context, R.layout.hole_layout, this)
        peekAnimator.setTarget(this.the_mole)
        hitAnimator.setTarget(this.the_mole)
        this.the_mole.setOnClickListener {
            if (currentState == State.Peek) {
                //peekAnimator.cancel()
                hitAnimator.start()
                onMoleClicked()
            }
        }
    }


    fun popUp() = sync {
        if (currentState == State.Idle) {
            peekAnimator.start()
        }
    }

    fun setOnMoleClickedListener(listener: (Int) -> Unit, id: Int) {
        onMoleClicked = { listener(id) }
    }
}