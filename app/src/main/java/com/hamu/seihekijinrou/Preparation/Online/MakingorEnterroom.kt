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


                binding.loading.visibility = View.VISIBLE
                collection
                        .get()
                        .addOnSuccessListener { document ->
                            if (!document.isEmpty()) {
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



                                val hekicheck = hashMapOf(
                                        "性癖" to hostheki
                                )
                                val info = hashMapOf(
                                        "名前1" to hostname,
                                        "性癖1" to hostheki
                                )
                                var namecheck = hashMapOf(
                                        "名前" to "$hostname"
                                )
                                var roomcheck = hashMapOf(
                                        "部屋" to "のかぶりを確認して、ゲームが始まったら削除する"
                                )
                                val countmember = hashMapOf(
                                        "1" to "1"
                                )
                                sameroomcheck.set(roomcheck)

                                var membernumber = collection.document("参加人数")
                                var playerinfo = collection.document("名前と性癖")

                                membernumber.set(countmember)
                                playerinfo.set(info)
                                collection.document("名前1").set(namecheck)
                                collection.document("性癖1").set(hekicheck)

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
                val sameroomcheck = collection.document("$searchtext")
                val membernumber =collection.document("参加人数")
                val playerinfo = collection.document("名前と性癖")

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
                                                collection
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
                                                                playerinfo
                                                                        .get()
                                                                        .addOnSuccessListener {
                                                                            var hostname = it.data?.get("名前1")!!
                                                                            binding.loading.visibility = View.INVISIBLE
                                                                            AlertDialog.Builder(this)
                                                                                    .setMessage("${hostname}さんの部屋が見つかりました")
                                                                                    .setPositiveButton("参加する") { dialog, which ->

                                                                                        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
                                                                                        pref.edit {
                                                                                            putString("roomname", searchtext.toString())
                                                                                                    .commit()
                                                                                        }


                                                                                        membernumber
                                                                                                .get()
                                                                                                .addOnSuccessListener {
                                                                                                    var count = it.data?.size
                                                                                                    var hekicheck = hashMapOf(
                                                                                                            "性癖" to "$guestheki"
                                                                                                    )
                                                                                                    var namecheck = hashMapOf(
                                                                                                            "名前" to "$guestheki"
                                                                                                    )
                                                                                                    if (count == 10) {
                                                                                                        AlertDialog.Builder(this)
                                                                                                                .setMessage("上限に達しています（１０人）。")
                                                                                                                .setPositiveButton("参加する") { dialog, which -> }.show()
                                                                                                    } else if (count == 9) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖10" to "$guestheki",
                                                                                                                "名前10" to "$guestname"
                                                                                                        )

                                                                                                        val countmember = hashMapOf(
                                                                                                                "10" to "10"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前10").set(namecheck)
                                                                                                        collection.document("性癖10").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 8) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖9" to "$guestheki",
                                                                                                                "名前9" to "$guestname"
                                                                                                        )

                                                                                                        val countmember = hashMapOf(
                                                                                                                "9" to "9"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前9").set(namecheck)
                                                                                                        collection.document("性癖9").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 7) {
                                                                                                        var info= hashMapOf(
                                                                                                                "性癖8" to "$guestheki",
                                                                                                                "名前8" to "$guestname"
                                                                                                        )
                                                                                                        val countmember = hashMapOf(
                                                                                                                "8" to "8"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前8").set(namecheck)
                                                                                                        collection.document("性癖8").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 6) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖7" to "$guestheki",
                                                                                                                "名前7" to "$guestname"
                                                                                                        )

                                                                                                        val countmember = hashMapOf(
                                                                                                                "7" to "7"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前7").set(namecheck)
                                                                                                        collection.document("性癖7").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 5) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖6" to "$guestheki",
                                                                                                                "名前6" to "$guestname"
                                                                                                        )

                                                                                                        val countmember = hashMapOf(
                                                                                                                "6" to "6"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前6").set(namecheck)
                                                                                                        collection.document("性癖6").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 4) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖5" to "$guestheki",
                                                                                                                "名前5" to "$guestname"
                                                                                                        )
                                                                                                        val countmember = hashMapOf(
                                                                                                                "5" to "5"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前5").set(namecheck)
                                                                                                        collection.document("性癖5").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 3) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖4" to "$guestheki",
                                                                                                                "名前4" to "$guestname"
                                                                                                        )

                                                                                                        val countmember = hashMapOf(
                                                                                                                "4" to "4"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前4").set(namecheck)
                                                                                                        collection.document("性癖4").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 2) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖3" to "$guestheki",
                                                                                                                "名前3" to "$guestname"
                                                                                                        )
                                                                                                        val countmember = hashMapOf(
                                                                                                                "3" to "3"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前3").set(namecheck)
                                                                                                        collection.document("性癖3").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    } else if (count == 1) {
                                                                                                        var info = hashMapOf(
                                                                                                                "性癖2" to "$guestheki",
                                                                                                                "名前2" to "$guestname"
                                                                                                        )
                                                                                                        val countmember = hashMapOf(
                                                                                                                "2" to "2"
                                                                                                        )
                                                                                                        playerinfo.set(info, SetOptions.merge())
                                                                                                        collection.document("名前2").set(namecheck)
                                                                                                        collection.document("性癖2").set(hekicheck)
                                                                                                        membernumber.set(countmember, SetOptions.merge())
                                                                                                    }


                                                                                                    if (count != 10) {
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














