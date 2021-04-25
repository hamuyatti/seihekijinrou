package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.FragmentWhendisagreeBinding

class Whendisagree : Fragment() {
    private var _binding:FragmentWhendisagreeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhendisagreeBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var Meetingnumber = pref.getString("ThistimeMeeting","")
        var roomname = pref.getString("roomname", "")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var docref = collection.document("gameinfo")



        return binding.root
    }


}