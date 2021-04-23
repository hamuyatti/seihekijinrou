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
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Online.CenterofOnlinegameprocess
import com.hamu.seihekijinrou.databinding.ActivityMakingorEnterroomBinding
import com.hamu.seihekijinrou.selectjinrou
import java.time.LocalDate
import java.time.LocalDateTime

class MakingorEnterroom : AppCompatActivity() {
    private lateinit var binding: ActivityMakingorEnterroomBinding
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMakingorEnterroomBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loading.visibility = View.INVISIBLE

        binding.makebutton.setOnClickListener {
            if (binding.passwordtext.text.isEmpty() || binding.hostname.text.isEmpty() || binding.hostseiheki.text.isEmpty()) {
                AlertDialog.Builder(this)
                        .setMessage("入力が完了していません。")
                        .setPositiveButton("もどる") { dialog, which ->
                        }.show()
            } else {
                var password = binding.passwordtext.text.toString()
                var hostname = binding.hostname.text.toString()
                var hostheki = binding.hostseiheki.text.toString()

                var collection: CollectionReference = db.collection("$password")
                val docRef = collection.document("gameinfo")

                binding.loading.visibility = View.VISIBLE
                docRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                binding.loading.visibility = View.INVISIBLE
                                AlertDialog.Builder(this)
                                        .setMessage("違う合言葉を使ってください。")
                                        .setPositiveButton("もどる") { dialog, which ->
                                        }.show()
                            } else {
                                val gameinfo = hashMapOf(
                                        "主催" to hostname,
                                )

                                val heki = hashMapOf(
                                        "性癖1" to hostheki
                                )
                                val name = hashMapOf(
                                        "名前1" to hostname
                                )
                                val countmember = hashMapOf(
                                        "参加人数" to "参加しました"
                                )

                                docRef.set(gameinfo)
                                docRef.collection("参加人数").add(countmember)
                                var namelist = collection.document("名前情報")
                                var seihekilist = collection.document("性癖情報")

                                seihekilist.set(heki)
                                namelist.set(name)

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
                val collection: CollectionReference = db.collection("$searchtext")
                val docRef = collection.document("gameinfo")
                val collection1 = docRef.collection("参加人数")
                val namelist = collection.document("名前情報")
                val seihekilist = collection.document("性癖情報")
                docRef.get()
                        .addOnSuccessListener {
                            if (!it.exists()) {
                                binding.loading.visibility = View.INVISIBLE
                                AlertDialog.Builder(this)
                                        .setMessage("部屋が見つかりませんでした。")
                                        .setPositiveButton("OK") { dialog, which ->

                                        }
                                        .show()
                            } else if (it.exists()) {
                                collection
                                        .whereEqualTo("名前", "$guestname")
                                        .get()
                                        .addOnSuccessListener { it ->
                                            if (!it.isEmpty) {
                                                binding.loading.visibility = View.INVISIBLE
                                                AlertDialog.Builder(this)
                                                        .setMessage("違う名前を使ってください。")
                                                        .setPositiveButton("もどる") { dialog, which ->
                                                        }.show()
                                            } else {
                                                docRef
                                                        .get()
                                                        .addOnSuccessListener {
                                                            var hostname = it.data?.get("主催")!!
                                                            binding.loading.visibility = View.INVISIBLE
                                                            AlertDialog.Builder(this)
                                                                    .setMessage("${hostname}さんの部屋が見つかりました")
                                                                    .setPositiveButton("参加する") { dialog, which ->
                                                                        val countmember = hashMapOf(
                                                                                "参加人数確認" to "参加しました"
                                                                        )

                                                                        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
                                                                        pref.edit {
                                                                            putString("roomname", searchtext.toString())
                                                                                    .commit()
                                                                        }

                                                                        collection1
                                                                                .get()
                                                                                .addOnSuccessListener {
                                                                                        var count = it.size()
                                                                                        if (count == 10) {
                                                                                            AlertDialog.Builder(this)
                                                                                                    .setMessage("上限に達しています（１０人）。")
                                                                                                    .setPositiveButton("参加する") { dialog, which -> }.show()
                                                                                        } else if (count == 9) {
                                                                                            var heki = hashMapOf(
                                                                                                "性癖10" to "$guestheki"
                                                                                        )
                                                                                            var name = hashMapOf(
                                                                                                    "名前10" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 8) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖9" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前9" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 7) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖8" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前8" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 6) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖7" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前7" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 5) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖6" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前6" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 4) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖5" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前5" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count== 3) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖4" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前4" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 2) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖3" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前3" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        } else if (count == 1) {
                                                                                            var heki = hashMapOf(
                                                                                                    "性癖2" to "$guestheki"
                                                                                            )
                                                                                            var name = hashMapOf(
                                                                                                    "名前2" to "$guestname"
                                                                                            )
                                                                                            seihekilist.set(heki, SetOptions.merge())
                                                                                            namelist.set(name, SetOptions.merge())
                                                                                        }
                                                                                    collection.document("gameinfo")
                                                                                            .collection("参加人数").add(countmember)
                                                                                     startActivity(Intent(this, Waitingentry::class.java))


                                                                                }
                                                                                }
                                                                                .setNegativeButton("やり直す") { dialog, which -> }
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














