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
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting3Binding

class onlineVoting3 : OnlineabstractVoting() {
    private lateinit var Suspect3: String

    private var _binding: FragmentOnlineVoting3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting3Binding.inflate(inflater, container, false)

        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("gameinfo").collection("投票3")

        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers3", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect3 = candidate1

                R.id.name2 -> Suspect3 = candidate2

                R.id.name3 -> Suspect3 = candidate3


            }
            binding.judge.setOnClickListener {
                members.remove(Suspect3)
                judge()
            }

        }


        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putString("Suspect3",Suspect3)
            putString("ThistimeSuspect",Suspect3)
        }.apply {  }
        if (Suspect3 == jinrouname) {

            findNavController().navigate(R.id.action_voting3_to_trueresult1)


        } else {
            pref.edit {
                putString("LOSE","負けた時の判別用です。")
            }.apply {  }

            findNavController().navigate(R.id.action_voting3_to_falseresult1)

        }
    }
}
