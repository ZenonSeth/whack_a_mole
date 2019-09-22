package com.milchopenchev.com.whac_a_mole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.milchopenchev.com.whac_a_mole.feature.whack.WhackFragment

class MainActivity : AppCompatActivity() {

    private val initFragTag = "init"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentByTag(initFragTag) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_holder, WhackFragment(), initFragTag)
                .commit()
        }
    }
}
