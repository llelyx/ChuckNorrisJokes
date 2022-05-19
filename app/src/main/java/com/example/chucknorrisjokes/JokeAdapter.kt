package com.example.chucknorrisjokes

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class JokeAdapter: RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    var jokes = mutableListOf<Joke>()
    var images = mutableListOf<Int>()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class JokeViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.itemImage)
        var itemText: TextView = view.findViewById(R.id.itemText)
        var shareButton: ImageButton = view.findViewById(R.id.shareButton)
        var saveButton: ImageButton = view.findViewById(R.id.saveButton)
        var unsaveButton: ImageButton = view.findViewById(R.id.unsaveButton)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): JokeViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return JokeViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: JokeViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the contents of the view with that element
        viewHolder.itemImage.setImageResource(images[position])
        viewHolder.itemText.text = jokes[position].value
        viewHolder.shareButton.setOnClickListener{
            Log.d("Response", "You've just shared the joke with the id " + jokes[position].id)

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my joke to send.")
                type = "text/plain"
            }

            // The following line doesn't work because we are in the adapter class
            // startActivity(Intent.createChooser(sendIntent, null))
        }
        viewHolder.saveButton.setOnClickListener {
            Log.d("Response", "You've just saved the joke with the id " + jokes[position].id)
            viewHolder.saveButton.visibility = View.INVISIBLE
            viewHolder.unsaveButton.visibility = View.VISIBLE
        }
        viewHolder.unsaveButton.setOnClickListener {
            Log.d("Response", "You've just unsaved the joke with the id " + jokes[position].id)
            viewHolder.unsaveButton.visibility = View.INVISIBLE
            viewHolder.saveButton.visibility = View.VISIBLE
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int{
        return jokes.size
    }

    // Update the list of jokes to add new ones from the Chuck Norris API
    fun updateJokes(joke: Joke) {
        jokes.add(joke)
        images.add(R.drawable.image)
        JokeAdapter().notifyDataSetChanged()
    }

    //fun onJokeMove(fromPosition: Int, toPosition: Int): Boolean

    fun onJokeDismiss(position: Int){
        jokes.remove(jokes[position])
    }


}