package com.example.dodo

import com.example.dodo.PizzaAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PizzaAdapter
    private lateinit var defaultImage: ImageView

    val pizzas = listOf(
        Pizza(1, "Баварская", "Острые колбаски чоризо, маринованные огурчики, красный лук, томаты, горчичный соус, моцарелла, фирменный томатный соус.", 2700, R.drawable.bavarskaya),
        Pizza(2, "Наруто Пицца", "Куриные кусочки, соус терияки, ананасы, моцарелла, фирменный соус альфредо", 3900, R.drawable.naruto),
        Pizza(3, "Пепперони с грибами","Пикантная пепперони, моцарелла, шампиньоны, соус альфредо",2000, R.drawable.peperoni_with_mushrooms),
        Pizza(4, "Сырная \uD83C\uDF31\uD83D\uDC76", "Моцарелла, сыры чеддер и пармезан, соус альфредо", 1900, R.drawable.cheesee),
        Pizza(5, "Двойной цыпленок \uD83D\uDC76", "Цыпленок, моцарелла, соус альфредо", 2100, R.drawable.double_chicken),
        Pizza(6, "Чоризо фреш \uD83C\uDF36\uFE0F", "Пикантные колбаски чоризо из цыпленка, зеленый перец, моцарелла, томатный соус", 1900, R.drawable.chorizo),
        Pizza(7, "Ветчина и сыр", "Ветчина, моцарелла и соус альфредо — просто и со вкусом", 2000, R.drawable.ham_and_cheese),
        Pizza(8, "Пепперони", "Пикантная пепперони, мно-о-ого моцареллы и томатный соус. Самая популярная пицца", 2700, R.drawable.peperoni),
        Pizza(9, "Четыре сыра \uD83C\uDF31", "Увеличенная порция моцареллы, сыры чеддер и пармезан, сыр блю чиз, фирменный соус альфредо", 2700, R.drawable.quatrocheese)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val myRecycler: RecyclerView = findViewById(R.id.myRecycler)
        val search_view: SearchView = findViewById(R.id.search_view)
        defaultImage = findViewById(R.id.default_image)



        adapter = PizzaAdapter(this, pizzas)

        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.adapter = adapter


        adapter.setOnItemClickListener(object : PizzaAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val selectedPizza = pizzas[position]

                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("selected_pizza", selectedPizza)
                startActivity(intent)


            }

        })

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    adapter.filter(it)
                    if (it.isEmpty() || adapter.itemCount == 0) {
                        myRecycler.visibility = View.GONE
                        defaultImage.visibility = View.VISIBLE
                    } else {
                        myRecycler.visibility = View.VISIBLE
                        defaultImage.visibility = View.GONE
                    }
                }
                return true
            }
        })
    }
}