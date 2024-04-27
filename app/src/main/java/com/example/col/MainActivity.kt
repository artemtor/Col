package com.example.col

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.colors, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()

        val radioText = dialogView.findViewById<RadioButton>(R.id.textButton)
        val radioSlider = dialogView.findViewById<RadioButton>(R.id.sliderButton)
        val textLayout = dialogView.findViewById<LinearLayout>(R.id.textLayout)
        val sliderLayout = dialogView.findViewById<LinearLayout>(R.id.sliderLayout)
        val createButton = dialogView.findViewById<Button>(R.id.createButton)
        val redEditText = dialogView.findViewById<EditText>(R.id.redEditText)
        val greenEditText = dialogView.findViewById<EditText>(R.id.greenEditText)
        val blueEditText = dialogView.findViewById<EditText>(R.id.blueEditText)
        val redSeekBar = dialogView.findViewById<SeekBar>(R.id.redSeekBar)
        val greenSeekBar = dialogView.findViewById<SeekBar>(R.id.greenSeekBar)
        val blueSeekBar = dialogView.findViewById<SeekBar>(R.id.blueSeekBar)

        val color = findViewById<View>(R.id.color)

        radioText.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textLayout.visibility = View.VISIBLE
                sliderLayout.visibility = View.GONE
            }
        }

        radioSlider.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textLayout.visibility = View.GONE
                sliderLayout.visibility = View.VISIBLE
            }
        }

        createButton.setOnClickListener {
            if (radioText.isChecked) {
                val redValue = redEditText.text.toString().toIntOrNull()
                val greenValue = greenEditText.text.toString().toIntOrNull()
                val blueValue = blueEditText.text.toString().toIntOrNull()

                if (redValue != null && greenValue != null && blueValue != null &&
                    redValue in 0..255 && greenValue in 0..255 && blueValue in 0..255
                ) {
                    val colors = android.graphics.Color.rgb(redValue, greenValue, blueValue)
                    color.setBackgroundColor(colors)
                    alertDialog.dismiss()
                } else {

                    Toast.makeText(this, "Введите значения от 0 до 255", Toast.LENGTH_SHORT).show()
                }
            } else if (radioSlider.isChecked) {
                val redValueBar = redSeekBar.progress
                val greenValueBar = greenSeekBar.progress
                val blueValueBar = blueSeekBar.progress
                val colors = android.graphics.Color.rgb(redValueBar, greenValueBar, blueValueBar)
                color.setBackgroundColor(colors)
                alertDialog.dismiss()

            }
        }

        val dialog = builder.create()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            dialog.show()
        }
    }
}