package com.example.dodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    private lateinit var pizzaPriceTextView: TextView
    private lateinit var sizeButtonGroup: RadioGroup
    private lateinit var doughButtonGroup: RadioGroup

    private lateinit var selectedPizza: Pizza
    private var pizzaPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val pizzaNameTextView: TextView = findViewById(R.id.details_name)
        val pizzaDescriptionTextView: TextView = findViewById(R.id.details_description)
        pizzaPriceTextView = findViewById(R.id.add_to_cart)

        val bundle : Bundle? = intent.extras
        selectedPizza = bundle?.getParcelable<Pizza>("selected_pizza") ?: return

        pizzaNameTextView.text = selectedPizza.name
        pizzaDescriptionTextView.text = selectedPizza.description
        pizzaPrice = selectedPizza.price
        updatePriceDisplay()

        sizeButtonGroup = findViewById(R.id.sizes)
        doughButtonGroup = findViewById(R.id.dough_type)

        sizeButtonGroup.setOnCheckedChangeListener { _, _ ->
            updatePrice()
        }

        doughButtonGroup.setOnCheckedChangeListener { _, _ ->
            updatePrice()
        }

        val buttons = listOf(
            findViewById<RadioButton>(R.id.small),
            findViewById<RadioButton>(R.id.medium),
            findViewById<RadioButton>(R.id.big),
            findViewById<RadioButton>(R.id.traditional_dough),
            findViewById<RadioButton>(R.id.thin_dough)
        )

        val selectedColor = resources.getColor(R.color.white)
        val defaultColor = resources.getColor(android.R.color.transparent)

        buttons.forEach { button ->
            button.setOnClickListener {
                buttons.forEach { otherButton ->
                    otherButton.setBackgroundColor(if (otherButton == button) selectedColor else defaultColor)
                }
            }
        }
    }

    private fun updatePrice() {
        val newSizePrice = when (sizeButtonGroup.checkedRadioButtonId) {
            R.id.small -> 0
            R.id.medium -> 500
            R.id.big -> 800
            else -> 0
        }

        val newDoughPrice = when (doughButtonGroup.checkedRadioButtonId) {
            R.id.traditional_dough -> 0
            R.id.thin_dough -> 200
            else -> 0
        }

        pizzaPrice = selectedPizza.price + newSizePrice + newDoughPrice
        updatePriceDisplay()
    }

    private fun updatePriceDisplay() {
        val priceText = "В КОРЗИНУ ЗА $pizzaPrice KZT"
        pizzaPriceTextView.text = priceText
    }
}
