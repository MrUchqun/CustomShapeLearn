package com.example.customshapelearn

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.net.URL
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var editText: TextInputEditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        editText = findViewById(R.id.ti_et_text)
        button = findViewById(R.id.btn_check)
        textView = findViewById(R.id.tv_result)

        button.setOnClickListener {
            textView.text = changeColorUrl(editText.text.toString())
        }
    }

    private fun changeColorUrl(text: String): Spannable {
        val array: List<String> = text.split(" ")
        val map: HashMap<Int, Int> = HashMap()
        for (item in array) {
            if (item != "" && item.isValidUrl()) {
                val start: Int = text.indexOf(item)
                val end: Int = start + item.length
                map[start] = end
            }
        }

        val spannable: Spannable = SpannableString(text)
        for ((k, v) in map) {
            spannable.setSpan(ForegroundColorSpan(Color.BLUE), k, v, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannable
    }

    private fun String.isValidUrl(): Boolean = Patterns.WEB_URL.matcher(this).matches()
}