package com.hamu.seihekijinrou.Preparation.Online

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.google.common.collect.Collections2
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Online.CenterofOnlinegameprocess
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.ActivityWaitingentryBinding

class Waitingentry : AppCompatActivity() {
    private lateinit var binding: ActivityWaitingentryBinding

    var db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWaitingentryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        var roomname = pref.getString("roomname", "")
        var collection1 = db.collection("$roomname").document("gameinfo").collection("参加人数")
        var collection2 = db.collection("$roomname").document("gameinfo").collection("ゲーム状況")
        var sameroomcheck = db.collection("$roomname").document("$roomname")
        var docref = db.collection("$roomname").document("gameinfo")

        collection1.addSnapshotListener { it, tmp ->
            collection1
                .get()
                .addOnSuccessListener {
                    var nownumberofmenber = it.size().toString()
                    binding.nownumberfmember.text = "$nownumberofmenber 人参加しています。"
                }
        }


        collection2.addSnapshotListener{it,tmp->
            collection2
                    .whereEqualTo("参加人数","参加しました")
                    .get()
                    .addOnSuccessListener {
                        if(!it.isEmpty){
                         startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                        }
                    }
        }


        binding.gotogame.setOnClickListener {
            collection1
                .get()
                .addOnSuccessListener {
                    if (it.size() == 2 || it.size() == 1) {
                        AlertDialog.Builder(this)
                            .setMessage("３人必要です。")
                            .setPositiveButton("戻る") { dialog, which ->
                            }.show()
                    } else {
                        AlertDialog.Builder(this)
                            .setMessage("進むとメンバーの変更が出来ません。よろしいですか？")
                            .setPositiveButton("進む") { dialog, which ->
                                pref.edit{
                                    putString("numberofpeople",it.size().toString())
                                }
                                val gamesituation = hashMapOf(
                                    "ゲーム状況" to "開始"
                                )
                                docref.collection("ゲーム状況").add(gamesituation)
                                sameroomcheck.delete()
                                startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                            }
                            .setNegativeButton("もどる") { dialog, which ->

                            }.show()
                    }

                }

        }
    }

}

