import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dodo.Pizza
import com.example.dodo.R

class PizzaAdapter(private val context: Context, private val pizzas: List<Pizza>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private var filteredPizzas = ArrayList<Pizza>(pizzas)

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mListener = listener

    }

    fun filter(text: String) {
        filteredPizzas.clear()
        if (text.isEmpty()) {
            filteredPizzas.addAll(pizzas)
        } else {
            val searchText = text.toLowerCase()
            pizzas.forEach { pizza ->
                if (pizza.name.toLowerCase().contains(searchText)) {
                    filteredPizzas.add(pizza)
                }
            }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pizza_row, parent, false)
        return PizzaViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = pizzas[position]
        holder.bind(pizza)
    }

    override fun getItemCount(): Int {
        return pizzas.size
    }


    inner class PizzaViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.pizza_name)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.pizza_description)
        private val priceTextView: TextView = itemView.findViewById(R.id.pizza_price)


        fun bind(pizza: Pizza) {
            imageView.setImageResource(pizza.imageResource)
            nameTextView.text = pizza.name
            descriptionTextView.text = pizza.description
            priceTextView.text = pizza.price
        }

        init {
            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }
        }
    }

}