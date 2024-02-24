package com.example.dodo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.dodo.R

class DetailsActivity : AppCompatActivity() {

    private lateinit var pizzaPriceTextView: TextView
    private lateinit var sizeButtonGroup: RadioGroup
    private lateinit var doughButtonGroup: RadioGroup

    private var pizzaPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val pizzaImg: ImageView = findViewById(R.id.imageView)
        val pizzaNameTextView: TextView = findViewById(R.id.details_name)
        val pizzaDescriptionTextView: TextView = findViewById(R.id.details_description)
        pizzaPriceTextView = findViewById(R.id.add_to_cart)

        val bundle : Bundle?= intent.extras
        val image = bundle!!.getInt("pizza_image")
        val name = bundle.getString("pizza_name")
        val description = bundle.getString("pizza_description")
        pizzaPrice = bundle.getInt("pizza_price")

        pizzaImg.setImageResource(image)
        pizzaNameTextView.text = name
        pizzaDescriptionTextView.text = description
        updatePriceDisplay()


        sizeButtonGroup = findViewById(R.id.sizes)
        doughButtonGroup = findViewById(R.id.dough_type)

        sizeButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            updatePrice()
        }

        doughButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            updatePrice()
        }



        val smallSizeButton = findViewById<RadioButton>(R.id.small)
        val mediumSizeButton = findViewById<RadioButton>(R.id.medium)
        val largeSizeButton = findViewById<RadioButton>(R.id.big)
        val traditionalDoughButton = findViewById<RadioButton>(R.id.traditional_dough)
        val thinDoughButton = findViewById<RadioButton>(R.id.thin_dough)

        val selectedColor = resources.getColor(R.color.white)
        val defaultColor = resources.getColor(android.R.color.transparent)

        val buttons = listOf(smallSizeButton, mediumSizeButton, largeSizeButton, traditionalDoughButton, thinDoughButton)
        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach { otherButton ->
                    if (otherButton == button) {
                        button.setBackgroundColor(selectedColor)
                    } else {
                        otherButton.setBackgroundColor(defaultColor)
                    }
                }
            }
        }
    }



    private fun updatePrice() {
        var newSizePrice = 0
        var newDoughPrice = 0

        newSizePrice = when (sizeButtonGroup.checkedRadioButtonId) {
            R.id.small -> 0
            R.id.medium -> 500
            R.id.big -> 800
            else -> 0
        }

        newDoughPrice = when (doughButtonGroup.checkedRadioButtonId) {
            R.id.traditional_dough -> 0
            R.id.thin_dough -> 200
            else -> 0
        }

        pizzaPrice += newSizePrice + newDoughPrice
        updatePriceDisplay()
    }

    private fun updatePriceDisplay() {
        val priceText = "В КОРЗИНУ ЗА $pizzaPrice KZT"
        pizzaPriceTextView.text = priceText
    }

}