package com.example.seihekijinrou.MeetingandVotingandResult


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentVoting4Binding


class Voting4 : Fragment() {
    private lateinit var Suspect4: String
    private lateinit var remainmembers3: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String

    private var _binding: FragmentVoting4Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting4Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")

        var fake = pref.getStringSet("remainmembers4", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
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

            members.remove(Suspect4)
            remainmembers3 = members.toSet()

            binding.judge.setOnClickListener {
                pref.edit {
                    putStringSet("remainmembers3", remainmembers3)
                    putString("Suspect4", Suspect4)
                    putString("ThistimeSuspect",Suspect4)
                }.apply { }
                if (Suspect4 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting4_to_trueresult1)


                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting4_to_falseresult1)

                }
            }

        }

        return binding.root
    }
}

