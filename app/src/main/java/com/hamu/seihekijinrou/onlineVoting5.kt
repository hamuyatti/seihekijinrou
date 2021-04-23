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
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting5Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting5Binding

class onlineVoting5 : OnlineabstractVoting() {
    private lateinit var Suspect5: String
    private lateinit var remainmembers4: Set<String>

    private var _binding: FragmentOnlineVoting5Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting5Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("gameinfo").collection("投票5")
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var tmp = pref.getStringSet("remainmembers5", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]


            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect5 = candidate1

                R.id.name2 -> Suspect5 = candidate2

                R.id.name3 -> Suspect5 = candidate3

                R.id.name4 -> Suspect5 = candidate4

                R.id.name5 -> Suspect5= candidate5


            }
            binding.judge.setOnClickListener {
                members.remove(Suspect5)
                remainmembers4 = members.toSet()
                judge()
            }
        }

        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers4", remainmembers4)
            putString("Suspect5", Suspect5)
            putString("ThistimeSuspect",Suspect5)
        }.apply { }
        if (Suspect5 == jinrouname) {


            findNavController().navigate(R.id.action_voting5_to_trueresult1)

        } else {

            findNavController().navigate(R.id.action_voting5_to_falseresult1)
        }
    }
}
