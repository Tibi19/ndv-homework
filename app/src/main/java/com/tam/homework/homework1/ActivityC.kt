package com.tam.homework.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.tam.homework.R

class ActivityC : AppCompatActivity(), View.OnClickListener {

    private val stringEditText: EditText by lazy { findViewById(R.id.et_string) }
    private val intEditText: EditText by lazy { findViewById(R.id.et_int) }

    companion object {
        const val ACTIVITY_C_OK = 1
        const val ACTIVITY_C_CANCEL = -1

        const val STRING_FOR_A_KEY = "STRING_FROM_C"
        const val INT_FOR_A_KEY = "INT_FROM_C"

        const val NO_DATA_FROM_A = "No data from A"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        setupStringFromA()
        setupIntFromA()

        listOf(
            R.id.btn_return_ok,
            R.id.btn_return_cancel,
            R.id.btn_c_to_b,
            R.id.btn_c_to_d
        ).forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener(this)
        }
    }

    private fun setupStringFromA() {
        val stringFromA: String = intent.extras?.run {
            get(ActivityA.STRING_FOR_C_KEY).toString()
        } ?: NO_DATA_FROM_A
        stringEditText.setText(stringFromA)
    }

    private fun setupIntFromA() {
        val intFromA: String = intent.extras?.run {
            get(ActivityA.INT_FOR_C_KEY).toString()
        } ?: NO_DATA_FROM_A
        intEditText.setText(intFromA)
    }

    override fun onClick(view: View?) = when(view?.id) {
        R.id.btn_return_ok -> finishActivityWithResult(ACTIVITY_C_OK)
        R.id.btn_return_cancel -> finishActivityWithResult(ACTIVITY_C_CANCEL)
        R.id.btn_c_to_b -> startActivitySimply(ActivityB::class.java)
        R.id.btn_c_to_d -> startActivitySimply(ActivityD::class.java)
        else -> {}
    }

    private fun finishActivityWithResult(resultCode: Int) = callingActivity?.let {
        val finishIntent = Intent(this, ActivityA::class.java)
        finishIntent.putExtra(STRING_FOR_A_KEY, getStringForA())
        finishIntent.putExtra(INT_FOR_A_KEY, getIntForA())
        setResult(resultCode, finishIntent)
        finish()
    } ?: Unit

    private fun getStringForA(): String = stringEditText.text.toString()

    private fun getIntForA(): Int =
        intEditText.text.toString().run {
            if (isEmpty()) -1 else toInt()
        }

}