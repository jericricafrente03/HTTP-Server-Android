package com.ph.bittelasia.meshtv.tv.meshhttpserver

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var mlist :MutableList<ItemsViewModel>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_http, parent, false)
        return ViewHolder(view)
    }
    fun addTodo(todo: ItemsViewModel) {
        mlist.add(todo)
        notifyItemInserted(mlist.size - 1)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun remove() {
        mlist.removeAll(mlist)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mlist[position]
        holder.textOne.text = data.textOne
        holder.textTwo.text = data.textTwo
        holder.textThree.text = data.textThree
    }
    override fun getItemCount(): Int {
        return mlist.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textOne: TextView = itemView.findViewById(R.id.textView)
        val textTwo: TextView = itemView.findViewById(R.id.textView2)
        val textThree: TextView = itemView.findViewById(R.id.textView3)
    }
}