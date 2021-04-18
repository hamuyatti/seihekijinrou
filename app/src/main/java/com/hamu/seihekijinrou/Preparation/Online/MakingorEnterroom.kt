package com.hamu.seihekijinrou.Preparation.Online

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.android.play.core.internal.al
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.ActivityMakingorEnterroomBinding

class MakingorEnterroom : AppCompatActivity() {
    private lateinit var binding:ActivityMakingorEnterroomBinding
    var db = Firebase.firestore
    private lateinit var item: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMakingorEnterroomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.makebutton.setOnClickListener {
            var password = binding.passwordtext.text
            var hostname = binding.hostname.text.toString()
            var collection: CollectionReference = db.collection("$password")
            val docRef = collection.document("$password")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists() ==true) {
                        AlertDialog.Builder(this)
                            .setMessage("違う合言葉を使ってください")
                            .setPositiveButton("OK") { dialog, which ->
                            }
                                .show()
                    }else{
                            val information = hashMapOf(
                                    "人数" to item,
                                    "主催" to hostname
                            )
                            collection.document("$password").set(information)


                        startActivity(Intent(this, Waitingentry::class.java))
                    }

                }

            }

        binding.searchbutton.setOnClickListener {
        var searchtext = binding.searchtext.text
            var collection: CollectionReference = db.collection("$searchtext")
            val docRef = collection.document("$searchtext")
            docRef.get()
                    .addOnSuccessListener { document ->
                        if (document.exists() ==true) {
                        collection
                                .whereEqualTo("主催",true)
                                .get()
                                .addOnSuccessListener { document ->
                                   var hostname = document.documentChanges.toString()
                                    AlertDialog.Builder(this)
                                            .setMessage("$hostname さんの部屋が見つかりました")
                                            .setPositiveButton("参加する") { dialog, which ->
                                                val information = hashMapOf(
                                                        "参加人数確認" to "参加しました"
                                                )
                                                docRef.set(information)
                                                startActivity(Intent(this, Waitingentry::class.java))
                                            }
                                            .setNegativeButton("やり直す") { dialog, which ->

                                            }
                                            .show()
                                    }

                        }else{
                            AlertDialog.Builder(this)
                                    .setMessage("部屋が見つかりませんでした。")
                                    .setPositiveButton("OK") { dialog, which ->

                                    }
                                    .show()
                        }
                    }


        }


        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                var spinner = parent as? Spinner
                item = (spinner?.selectedItem as? String).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                item = "３人"
            }
        }


        }



    }
