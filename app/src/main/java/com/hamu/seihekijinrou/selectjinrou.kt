package com.hamu.seihekijinrou

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.FragmentSelectjinrouBinding
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import java.util.jar.Manifest

class selectjinrou : Fragment() {
    private var _binding: FragmentSelectjinrouBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSelectjinrouBinding.inflate(layoutInflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname" ,"")
        var collection = db.collection("$roomname")

        data class seihekicollection(
                val heki1: String? = null,
                val heki2: String? = null,
                val heki3: String? = null,
                val heki4: String? = null,
                val heki5: String? = null,
                val heki6: String? = null,
                val heki7: String? = null,
                val heki8: String? = null,
                val heki9: String? = null,
                val heki10: String? = null
        )
        collection
                .whereEqualTo("性癖", true)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents){
                       binding.jinrou.text = document.data.get("性癖").toString()
                    }
                }
        return binding.root
    }
}