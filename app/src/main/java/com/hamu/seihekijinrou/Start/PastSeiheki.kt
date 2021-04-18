package com.hamu.seihekijinrou.Start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamu.seihekijinrou.databinding.ActivityPastseihekiBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PastSeiheki : AppCompatActivity() {
    var db = Firebase.firestore
    var collection: CollectionReference = db.collection("users")
    var adapter: Adapter? = null


    private lateinit var binding: ActivityPastseihekiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPastseihekiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerview()



        binding.searchbutton.setOnClickListener{
            var searchtext =binding.searchtext.text
            collection
                    .whereEqualTo("seiheki", "$searchtext")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            binding.numbertext.text = "${document.documents.size.toString()}回"

                        }
                    }
        }

    }

        fun setupRecyclerview() {
            val query: Query = collection
            val firestoreRecycleroption: FirestoreRecyclerOptions<Model> = FirestoreRecyclerOptions.Builder<Model>().setQuery(query, Model::class.java).build()

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

