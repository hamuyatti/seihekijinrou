package com.hamu.seihekijinrou.Preparation.Online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.ActivityWaitingentryBinding

class Waitingentry : AppCompatActivity() {
    private lateinit var binding: ActivityWaitingentryBinding
    lateinit var collection: CollectionReference
    var db = Firebase.firestore
    lateinit var nownumberofmenber:String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWaitingentryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        var roomname = pref.getString("roomname", "")
        collection = db.collection("$roomname").document("gameinfo").collection("参加人数")

        collection
                .get()
                .addOnSuccessListener {
                    var nownumberofmenber = it.size().toString()
                    binding.nownumberfmember.text = "$nownumberofmenber 人参加しています。"

                }
        collection.whereEqualTo("人数", true)
                .get()
                .addOnSuccessListener {document->
                       var tmp = document
                        binding.settingedplayernumber.text = "$tmp で設定されています。今の人数で参加しますか？"
                        pref.edit {
                            putString("人数", document.toString())
                        }
                        var settingednumber = "($document)人"
                       /* if (settingednumber == nownumberofmenber) {

                        }*/


                }
    }
}