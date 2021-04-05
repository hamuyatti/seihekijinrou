package com.example.seihekijinrou.Start

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.ActivitySeihekiRakingBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SeihekiRaking : AppCompatActivity() {
    var db = Firebase.firestore
    var collection :CollectionReference = db.collection("users")
    var adapter:Adapter? = null

    private lateinit var binding:ActivitySeihekiRakingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySeihekiRakingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setupRecyclerview()

    }


    fun setupRecyclerview(){
       val query:Query = collection
       val firestoreRecycleroption:FirestoreRecyclerOptions<Model> = FirestoreRecyclerOptions.Builder<Model>().setQuery(query,Model::class.java).build()

       adapter = Adapter(firestoreRecycleroption)

        binding.list.layoutManager = LinearLayoutManager(this)

        binding.list.adapter = adapter

    }


    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter!!.stopListening()
    }

}