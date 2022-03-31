package com.example.chucknorrisjokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JokeAdapter: RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {
    private val jokes = listJokes

    private var titles = arrayOf("Joke 1", "Joke 2", "Joke 3", "Joke 4", "Joke 5", "Joke 6",
        "Joke 7", "Joke 8", "Joke 9", "Joke 10")

    private val images = intArrayOf(R.drawable.image, R.drawable.image, R.drawable.image, R.drawable.image,
        R.drawable.image, R.drawable.image, R.drawable.ic_launcher_background, R.drawable.image, R.drawable.image,
        R.drawable.image)

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class JokeViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.item_image)
        var itemTitle: TextView = view.findViewById(R.id.item_title)
        var itemText: TextView = view.findViewById(R.id.item_text)

//        init {
////             Define click listener for the ViewHolder's View.
//
//            view.setOnClickListener{
//                val position: Int = adapterPosition
//
//                Toast.makeText(view.context, "You clicked on ${titles[position]}", Toast.LENGTH_LONG).show()
//            }
//        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): JokeViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return JokeViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: JokeViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemImage.setImageResource(images[position])
        viewHolder.itemTitle.text = titles[position]
        viewHolder.itemText.text = jokes[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int{
        return titles.size
    }

//    fun updateJokes(list: List<String>) {
//        val jokes = jokes + list
//        notifyDataSetChanged()
//    }


}