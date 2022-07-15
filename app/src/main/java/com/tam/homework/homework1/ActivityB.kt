package com.tam.homework.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.tam.homework.R

class ActivityB : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        listOf(
            R.id.btn_b_to_a,
            R.id.btn_b_to_b,
            R.id.btn_b_to_c,
            R.id.btn_b_to_d
        ).forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) = when(view?.id) {
        R.id.btn_b_to_a -> startActivitySimply(ActivityA::class.java)
        R.id.btn_b_to_b -> startActivitySimply(ActivityB::class.java)
        R.id.btn_b_to_c -> startActivitySimply(ActivityC::class.java)
        R.id.btn_b_to_d -> startActivitySimply(ActivityD::class.java)
        else -> {}
    }

}