package com.hamu.seihekijinrou.Preparation.Online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.ActivityWaitingentryBinding

class Waitingentry : AppCompatActivity() {
    private lateinit var binding: ActivityWaitingentryBinding
    var db = Firebase.firestore
    var collection: CollectionReference = db.collection("users")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWaitingentryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waitingentry)

        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        var settingedplayernumber = pref.getString("numberofpeople","")
        binding.settingedplayernumber.text = settingedplayernumber
    }
}