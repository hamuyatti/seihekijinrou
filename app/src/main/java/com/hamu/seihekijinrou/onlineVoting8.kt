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
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting8Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting8Binding


class onlineVoting8: OnlineabstractVoting(){
    private lateinit var Suspect8: String
    private lateinit var remainmembers7: Set<String>

    private var _binding: FragmentOnlineVoting8Binding? = null
    private val binding get() = _binding!!

    var pref = PreferenceManager.getDefaultSharedPreferences(context)
    var roomname = pref.getString("roomname", "")
    var db = Firebase.firestore
    var collection = db.collection("$roomname")
    var Voting = collection.document("gameinfo").collection("投票8")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting8Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers8", setOf(""))
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


            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7
            binding.name8.text = candidate8

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect8 = candidate1

                R.id.name2 -> Suspect8 = candidate2

                R.id.name3 -> Suspect8 = candidate3

                R.id.name4 -> Suspect8 = candidate4

                R.id.name5 -> Suspect8 = candidate5

                R.id.name6 -> Suspect8 = candidate6

                R.id.name7 -> Suspect8 = candidate7

                R.id.name8 -> Suspect8 = candidate8


            }

            binding.judge.setOnClickListener {
                members.remove(Suspect8)
                remainmembers7 = members.toSet() as Set<String>
                judge()

            }

        }


        return binding.root
    }


    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers7", remainmembers7)
            putString("Suspect8", Suspect8)
            putString("ThistimeSuspect", Suspect8)
        }.apply { }
        if (Suspect8 == jinrouname) {

            findNavController().navigate(R.id.action_voting8_to_trueresult1)

        } else {

            findNavController().navigate(R.id.action_voting8_to_falseresult1)
        }
    }
}

