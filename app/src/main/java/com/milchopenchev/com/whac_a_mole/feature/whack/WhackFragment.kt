package com.milchopenchev.com.whac_a_mole.feature.whack

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.milchopenchev.com.whac_a_mole.R
import kotlinx.android.synthetic.main.whack_fragment_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class WhackFragmentData : ViewModel() {
    var score: Int = 0
}

class WhackFragment : Fragment(R.layout.whack_fragment_layout) {

    private lateinit var model: WhackFragmentData

    private val rand = Random(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.model = ViewModelProviders.of(this).get(WhackFragmentData::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateScore()
        setupClickListeners(view)
        lifecycleScope.launch {
            while (true) {
                delay(1000)  // between scheduling next event
                scheduleEvent()
            }
        }
    }

    private fun updateScore() {
        view?.score?.text = "Score: ${model.score}"
    }

    private fun setupClickListeners(view: View) {
        val listener = { _: Int ->
            model.score++
            updateScore()
        }
        view.hole_1.setOnMoleClickedListener(listener, 0)
        view.hole_2.setOnMoleClickedListener(listener, 1)
        view.hole_3.setOnMoleClickedListener(listener, 2)
        view.hole_4.setOnMoleClickedListener(listener, 3)
    }

    private fun scheduleEvent() = launchIo {
        delay(100 + rand.nextLong(3000))
        launchMain {
            when (rand.nextInt(4)) {
                0 -> view?.hole_1?.popUp()
                1 -> view?.hole_2?.popUp()
                2 -> view?.hole_3?.popUp()
                3 -> view?.hole_4?.popUp()
            }
        }
    }

    private fun launchIo(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(context = Dispatchers.IO, block = block)
    }

    private fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(context = Dispatchers.Main, block = block)
    }
}