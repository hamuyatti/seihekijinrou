package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.FragmentSelectjinrouBinding

class selectjinrou : Fragment() {
    private var _binding:FragmentSelectjinrouBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSelectjinrouBinding.inflate(layoutInflater)

        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var collection = db.collection("$roomname")


        return binding.root
    }

}