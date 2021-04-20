package com.hamu.seihekijinrou.Preparation.Online

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.ActivityMakingorEnterroomBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MakingorEnterroom : AppCompatActivity() {
    private lateinit var binding: ActivityMakingorEnterroomBinding
    var db = Firebase.firestore
    private lateinit var item: String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMakingorEnterroomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loading.visibility = View.INVISIBLE

        binding.makebutton.setOnClickListener {
            if (binding.passwordtext.text.isEmpty()|| binding.hostname.text.isEmpty() || binding.hostseiheki.text.isEmpty()) {
                AlertDialog.Builder(this)
                        .setMessage("入力が完了していません。")
                        .setPositiveButton("もどる") { dialog, which ->
                        }.show()
            }
            else {
                var password = binding.passwordtext.text.toString()
                var hostname = binding.hostname.text.toString()
                var hostheki = binding.hostseiheki.text.toString()

                var collection: CollectionReference = db.collection("$password")
                val docRef = collection.document("gameinfo")

                binding.loading.visibility = View.VISIBLE
                docRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists() == true) {
                                binding.loading.visibility = View.INVISIBLE
                                AlertDialog.Builder(this)
                                        .setMessage("違う合言葉を使ってください。")
                                        .setPositiveButton("もどる") { dialog, which ->
                                        }.show()
                            } else {
                                val gameinfo = hashMapOf(
                                        "主催" to hostname,
                                )

                                val memberinfo = hashMapOf(
                                        "名前" to hostname,
                                        "性癖" to hostheki
                                )
                                val tmp = hashMapOf(
                                        "参加人数" to "参加しました"
                                )

                                docRef.set(gameinfo)
                                docRef.collection("参加人数").add(tmp)
                                collection.document("$hostname").set(memberinfo)


                                var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
                                pref.edit {
                                    putString("roomname", password)
                                            .commit()
                                }
                                startActivity(Intent(this, Waitingentry::class.java))
                            }


                        }
            }
            }

            binding.searchbutton.setOnClickListener {
                var searchtext = binding.searchtext.text
                var guestheki = binding.guestseiheki.text
                var guestname = binding.guestname.text

                if (searchtext.isEmpty() || guestheki.isEmpty() || guestname.isEmpty()) {
                    AlertDialog.Builder(this)
                            .setMessage("入力が完了していません。")
                            .setPositiveButton("もどる") { dialog, which ->
                            }.show()
                } else {
                    binding.loading.visibility = View.VISIBLE
                    var collection: CollectionReference = db.collection("$searchtext")
                    val docRef = collection.document("$searchtext")
                    docRef.get()
                            .addOnSuccessListener { tmp ->
                                if (tmp.exists() == false) {
                                    binding.loading.visibility = View.INVISIBLE
                                    AlertDialog.Builder(this)
                                            .setMessage("部屋が見つかりませんでした。")
                                            .setPositiveButton("OK") { dialog, which ->

                                            }
                                            .show()
                                } else if (tmp.exists() == true) {
                                    collection.document("$guestname").get()
                                            .addOnSuccessListener { tmp2 ->
                                                if (tmp2.exists() == true) {
                                                    binding.loading.visibility = View.INVISIBLE
                                                    AlertDialog.Builder(this)
                                                            .setMessage("違う名前を使ってください。")
                                                            .setPositiveButton("もどる") { dialog, which ->
                                                            }.show()
                                                } else {
                                                    collection
                                                            .whereEqualTo("主催", true)
                                                            .get()
                                                            .addOnSuccessListener { document ->
                                                                var hostname = document.documents.toString()
                                                                binding.loading.visibility = View.INVISIBLE
                                                                AlertDialog.Builder(this)
                                                                        .setMessage("$hostname さんの部屋が見つかりました")
                                                                        .setPositiveButton("参加する") { dialog, which ->
                                                                            val gameinfo = hashMapOf(
                                                                                    "参加人数確認" to "参加しました"
                                                                            )
                                                                            val memberinfo = hashMapOf(
                                                                                    "名前" to "$guestname",
                                                                                    "性癖" to "$guestheki"
                                                                            )
                                                                            collection.document("gameinfo")
                                                                                    .collection("参加人数").add(gameinfo)
                                                                            collection.document("$guestname").set(memberinfo)

                                                                            var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
                                                                            pref.edit {
                                                                                putString("roomname", searchtext.toString())
                                                                                        .commit()
                                                                            }


                                                                            startActivity(Intent(this, Waitingentry::class.java))
                                                                        }
                                                                        .setNegativeButton("やり直す") { dialog, which ->

                                                                        }
                                                                        .show()
                                                            }
                                                }
                                            }

                                }
                            }
                }


            }


        }
    }



