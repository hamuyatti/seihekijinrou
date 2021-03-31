package com.example.seihekijinrou.Start

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class Seihekiadapter(data: OrderedRealmCollection<seihekidata>)
    :RealmRecyclerViewAdapter<seihekidata,Seihekiadapter.ViewHolder>(data,true) {

    class ViewHolder(cell: View):RecyclerView.ViewHolder(cell){
        val seiheki: TextView = cell.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Seihekiadapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Seihekiadapter.ViewHolder, position: Int) {
        val seihekidata: seihekidata? = getItem(position)
        holder.seiheki.text = seihekidata?.seiheki
    }


}

