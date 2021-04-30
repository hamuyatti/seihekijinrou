package com.hamu.seihekijinrou.Preparation.Online

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.ActivityMakingorEnterroomBinding
import com.hamu.seihekijinrou.Preparation.Online.selectjinrou
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
                val sameroomcheck = collection.document("$password")
                val docRef = collection.document("gameinfo")
                val samenamecheck = docRef.collection("namecheck")
                val samehekicheck = docRef.collection("seihekicheck")

                binding.loading.visibility = View.VISIBLE
                sameroomcheck.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                binding.loading.visibility = View.INVISIBLE
                                AlertDialog.Builder(this)
                                        .setMessage("違う合言葉を使ってください。")
                                        .setPositiveButton("もどる") { dialog, which ->
                                        }.show()
                            } else {

                                val text = "部屋を作成しました。"
                                val duration = Toast.LENGTH_SHORT
                                val toast = Toast.makeText(this, text, duration)
                                toast.show()

                                val gameinfo = hashMapOf(
                                        "主催" to hostname,
                                )

                                val heki = hashMapOf(
                                        "性癖1" to hostheki
                                )
                                val hekicheck = hashMapOf(
                                        "性癖" to hostheki
                                )
                                val name = hashMapOf(
                                        "名前1" to hostname
                                )
                                val countmember = hashMapOf(
                                        "参加人数" to "参加しました"
                                )
                                var namecheck = hashMapOf(
                                        "名前" to "$hostname"
                                )
                                var roomcheck = hashMapOf(
                                        "部屋" to "のかぶりを確認して、ゲームが始まったら削除する"
                                )
                                sameroomcheck.set(roomcheck)
                                samenamecheck.add(namecheck)
                                samehekicheck.add(hekicheck)
                                docRef.set(gameinfo)
                                docRef.collection("参加人数").add(countmember)
                                var namelist = collection.document("名前情報")
                                var seihekilist = collection.document("性癖情報")

                                seihekilist.set(heki)
                                namelist.set(name)

                                var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
                                pref.edit {
                                    putString("roomname", password).commit()
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
                val samenamecheck = docRef.collection("namecheck")
                val sameroomcheck = collection.document("$searchtext")
                val samehekicheck = docRef.collection("seihekicheck")
                val collection1 = docRef.collection("参加人数")
                val namelist = collection.document("名前情報")
                val seihekilist = collection.document("性癖情報")
                sameroomcheck.get()
                        .addOnSuccessListener {
                            if (!it.exists()) {
                                binding.loading.visibility = View.INVISIBLE
                                AlertDialog.Builder(this)
                                        .setMessage("部屋が見つかりませんでした。")
                                        .setPositiveButton("OK") { dialog, which ->
                                        }
                                        .show()
                            } else if (it.exists()) {
                                samenamecheck
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
                                                samehekicheck
                                                        .whereEqualTo("性癖", "$guestheki")
                                                        .get()
                                                        .addOnSuccessListener {
                                                            if (!it.isEmpty) {
                                                                binding.loading.visibility = View.INVISIBLE
                                                                AlertDialog.Builder(this)
                                                                        .setMessage("違う性癖を入力してください。")
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
                                                                                                    var hekicheck = hashMapOf(
                                                                                                            "性癖" to "$guestheki"
                                                                                                    )
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
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 8) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖9" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前9" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 7) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖8" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前8" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 6) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖7" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前7" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 5) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖6" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前6" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 4) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖5" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前5" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 3) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖4" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前4" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 2) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖3" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前3" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    } else if (count == 1) {
                                                                                                        var heki = hashMapOf(
                                                                                                                "性癖2" to "$guestheki"
                                                                                                        )
                                                                                                        var name = hashMapOf(
                                                                                                                "名前2" to "$guestname"
                                                                                                        )
                                                                                                        seihekilist.set(heki, SetOptions.merge())
                                                                                                        namelist.set(name, SetOptions.merge())
                                                                                                        samehekicheck.add(hekicheck)
                                                                                                    }


                                                                                                    if (count != 10) {
                                                                                                        var namecheck = hashMapOf(
                                                                                                                "名前" to "$guestname"
                                                                                                        )
                                                                                                        samenamecheck.add(namecheck)
                                                                                                        collection.document("gameinfo")
                                                                                                                .collection("参加人数").add(countmember)
                                                                                                        startActivity(Intent(this, Waitingentry::class.java))

                                                                                                    }
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
    }
    override fun onResume() {
        super.onResume()
        binding.loading.visibility = View.INVISIBLE
    }
 }














