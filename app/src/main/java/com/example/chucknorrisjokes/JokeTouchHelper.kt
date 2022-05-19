package com.example.chucknorrisjokes

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class JokeTouchHelper(
    private val onJokeRemoved: JokeAdapter ,
    private val onItemMoved: JokeAdapter
) : ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        UP or DOWN,
        LEFT or RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            recyclerView.adapter!!.notifyItemMoved(fromPosition,toPosition)
            recyclerView.adapter!!.notifyItemRangeChanged(0, max(fromPosition, toPosition), Any())
            return true

        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            onJokeRemoved.onJokeDismiss(viewHolder.adapterPosition)
        }

    }
)