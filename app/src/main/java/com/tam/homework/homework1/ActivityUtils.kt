package com.tam.homework.homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun <T : AppCompatActivity> AppCompatActivity.startActivitySimply(javaClassActivity: Class<T>) {
    val startActivityIntent = Intent(this, javaClassActivity)
    startActivity(startActivityIntent)
}