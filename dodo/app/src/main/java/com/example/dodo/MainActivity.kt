package com.example.dodo

import PizzaAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val pizzas = listOf(
        Pizza("Баварская", "Острые колбаски чоризо, маринованные огурчики, красный лук, томаты, горчичный соус, моцарелла, фирменный томатный соус.", "от 2700 тг.", R.drawable.bavarskaya),
        Pizza("Наруто Пицца", "Куриные кусочки, соус терияки, ананасы, моцарелла, фирменный соус альфредо", "3900 тг.", R.drawable.naruto),
        Pizza("Пепперони с грибами","Пикантная пепперони, моцарелла, шампиньоны, соус альфредо","от 2000тг.", R.drawable.peperoni_with_mushrooms),
        Pizza("Сырная \uD83C\uDF31\uD83D\uDC76", "Моцарелла, сыры чеддер и пармезан, соус альфредо", "от 1900 тг.", R.drawable.cheesee),
        Pizza("Двойной цыпленок \uD83D\uDC76", "Цыпленок, моцарелла, соус альфредо", "от 2 100тг.", R.drawable.double_chicken),
        Pizza("Чоризо фреш \uD83C\uDF36\uFE0F", "Пикантные колбаски чоризо из цыпленка, зеленый перец, моцарелла, томатный соус", "от 1 900тг.", R.drawable.chorizo),
        Pizza("Ветчина и сыр", "Ветчина, моцарелла и соус альфредо — просто и со вкусом", "от 2 000тг.", R.drawable.ham_and_cheese),
        Pizza("Пепперони", "Пикантная пепперони, мно-о-ого моцареллы и томатный соус. Самая популярная пицца", "от 2700тг.", R.drawable.peperoni),
        Pizza("Четыре сыра \uD83C\uDF31", "Увеличенная порция моцареллы, сыры чеддер и пармезан, сыр блю чиз, фирменный соус альфредо", "от 2700тг.", R.drawable.quatrocheese)
    )

    private lateinit var adapter: PizzaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRecycler: RecyclerView = findViewById(R.id.myRecycler)
        val search_view: SearchView = findViewById(R.id.search_view)

        adapter = PizzaAdapter(this, pizzas)

        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.adapter = adapter

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    adapter.filter(it)
                    myRecycler.visibility = if (it.isEmpty() || adapter.itemCount == 0) View.GONE else View.VISIBLE
                }
                return true
            }
        })
    }
}