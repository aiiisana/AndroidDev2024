import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dodo.DetailsActivity
import com.example.dodo.Pizza
import com.example.dodo.R

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PizzaAdapter
    private lateinit var defaultImage: ImageView
    private lateinit var searchEditText: EditText

    private val allPizzas = listOf( Pizza(1, "Баварская", "Острые колбаски чоризо, маринованные огурчики, красный лук, томаты, горчичный соус, моцарелла, фирменный томатный соус.", 2700, R.drawable.bavarskaya),
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
        defaultImage = findViewById(R.id.default_image)
        searchEditText = findViewById(R.id.search_edit_text)

        adapter = PizzaAdapter(this, allPizzas)
        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.adapter = adapter

        adapter.setOnItemClickListener(object : PizzaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedPizza = allPizzas[position]
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("selected_pizza", selectedPizza)
                startActivity(intent)
            }
        })


        // Обработчик нажатия на кнопку поиска
        val searchButton: Button = findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                performSearch(query)
            } else {
                Toast.makeText(this, "Введите запрос для поиска", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performSearch(query: String) {
        val filteredList = allPizzas.filter { pizza ->
            pizza.name.contains(query, ignoreCase = true) ||
                    pizza.description.contains(query, ignoreCase = true)
        }
        if (filteredList.isNotEmpty()) {
            adapter.filterList(filteredList)
            defaultImage.visibility = View.GONE
        } else {
            adapter.filterList(emptyList())
            defaultImage.visibility = View.VISIBLE
        }
    }
}
