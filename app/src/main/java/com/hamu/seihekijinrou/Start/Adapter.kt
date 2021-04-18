package com.hamu.seihekijinrou.Start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class Adapter(options: FirestoreRecyclerOptions<Model>) :
    FirestoreRecyclerAdapter<Model, Adapter.ViewHolder>(options) {

     class ViewHolder(cell: View) : RecyclerView.ViewHolder(cell) {
         val heki: TextView = cell.findViewById(android.R.id.text1)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater =LayoutInflater.from(parent.context)
        var view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Model) {
        holder.heki.text = model.seiheki
    }

}



