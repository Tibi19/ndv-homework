package com.tam.homework.homework1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.tam.homework.R

class ActivityA : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val REQUEST_VIEW_URL = 0
        const val URL = "https://developer.android.com/"

        const val REQUEST_C_RESULT = 101
        const val RESULT_FROM_C_TAG = "RESULT_FROM_C"

        const val STRING_FOR_C_KEY = "STRING_FOR_C"
        const val STRING_FOR_C = "Pickle"
        const val INT_FOR_C_KEY = "INT_FOR_C"
        const val INT_FOR_C = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        logLifecycle("OnCreate")

        listOf(
            R.id.btn_open_url,
            R.id.btn_go_to_b,
            R.id.btn_go_to_c,
            R.id.btn_go_to_d
        ).forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener(this)
        }
    }

    private fun logLifecycle(message: String) = Log.d("LIFECYCLE", message)

    override fun onClick(view: View?) = when(view?.id) {
        R.id.btn_open_url -> openUrl(URL)
        R.id.btn_go_to_b -> startActivitySimply(ActivityB::class.java)
        R.id.btn_go_to_c -> startActivityCWithRegister() // Deprecated version: startActivityCForResult()
        R.id.btn_go_to_d -> startActivitySimply(ActivityD::class.java)
        else -> {}
    }

    private fun openUrl(url: String) {
        val openUrlIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivityForResult(openUrlIntent, REQUEST_VIEW_URL)
    }

    private fun startActivityCForResult() = startActivity(getStartCIntent())

    private fun getStartCIntent(): Intent =
        Intent(this, ActivityC::class.java).apply {
            putExtra(STRING_FOR_C_KEY, STRING_FOR_C)
            putExtra(INT_FOR_C_KEY, INT_FOR_C)
        }

    private val startCForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.d(RESULT_FROM_C_TAG, "This is the new way")
            onActivityCResult(result.resultCode, result.data)
        }

    private fun startActivityCWithRegister() = startCForResult.launch(getStartCIntent())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(RESULT_FROM_C_TAG, "This is the old way")
        onActivityCResult(resultCode, data)
    }

    private fun onActivityCResult(resultCode: Int, data:Intent?) {
        Log.d(RESULT_FROM_C_TAG, "result code: $resultCode")

        val stringFromC = data?.getStringExtra(ActivityC.STRING_FOR_A_KEY) ?: "X"
        val intFromC = data?.getIntExtra(ActivityC.INT_FOR_A_KEY, -1) ?: -1
        Log.d(RESULT_FROM_C_TAG, "String from C: $stringFromC")
        Log.d(RESULT_FROM_C_TAG, "Int from C: $intFromC")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("OnStart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("OnResume")
    }

    override fun onPause() {
        super.onPause()
        logLifecycle("OnPause")
    }

    override fun onStop() {
        super.onStop()
        logLifecycle("OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycle("OnDestroy")
    }

}