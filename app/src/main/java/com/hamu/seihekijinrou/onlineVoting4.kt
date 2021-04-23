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
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting4Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting4Binding

class onlineVoting4 : OnlineabstractVoting() {
    private lateinit var Suspect4: String
    private lateinit var remainmembers3: Set<String>

    private var _binding: FragmentOnlineVoting4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting4Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("gameinfo").collection("投票4")
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers4", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect4 = candidate1

                R.id.name2 -> Suspect4 = candidate2

                R.id.name3 -> Suspect4 = candidate3

                R.id.name4 -> Suspect4 = candidate4

            }
            binding.judge.setOnClickListener {
                members.remove(Suspect4)
                remainmembers3 = members.toSet()
                judge()
            }


        }

        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers3", remainmembers3)
            putString("Suspect4", Suspect4)
            putString("ThistimeSuspect",Suspect4)
        }.apply { }
        if (Suspect4 == jinrouname) {


            findNavController().navigate(R.id.action_voting4_to_trueresult1)


        } else {

            findNavController().navigate(R.id.action_voting4_to_falseresult1)

        }
    }
}

