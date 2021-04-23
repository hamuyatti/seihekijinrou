package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Voting.abstractVoting
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting9Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting9Binding

class onlineVoting9: OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting9Binding? = null
    private val binding get() = _binding!!
    private lateinit var Suspect9: String
    private lateinit var remainmembers8: Set<String>

    var pref = PreferenceManager.getDefaultSharedPreferences(context)
    var roomname = pref.getString("roomname", "")
    var db = Firebase.firestore
    var collection = db.collection("$roomname")
    var Voting = collection.document("gameinfo").collection("投票9")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting9Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers9", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]
            candidate7 = members[6]
            candidate8 = members[7]
            candidate9 = members[8]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7
            binding.name8.text = candidate8
            binding.name9.text = candidate9

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect9 = candidate1

                R.id.name2 -> Suspect9 = candidate2

                R.id.name3 -> Suspect9 = candidate3

                R.id.name4 -> Suspect9 = candidate4

                R.id.name5 -> Suspect9 = candidate5

                R.id.name6 -> Suspect9 = candidate6

                R.id.name7 -> Suspect9 = candidate7

                R.id.name8 -> Suspect9 = candidate8

                R.id.name9 -> Suspect9 = candidate9
            }
            binding.judge.setOnClickListener {
                members.remove(Suspect9)
                remainmembers8 = members.toSet() as Set<String>
                judge()
            }

        }


        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers8", remainmembers8)
            putString("Suspect9", Suspect9)
            putString("ThistimeSuspect",Suspect9)}.apply {  }
        if (Suspect9 == jinrouname) {
            findNavController().navigate(R.id.action_voting9_to_trueresult1)

        } else {
            findNavController().navigate(R.id.action_voting9_to_falseresult1)
        }
    }

}