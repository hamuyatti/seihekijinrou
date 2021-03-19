package com.example.seihekijinrou

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.FragmentVoting10Binding
import com.example.seihekijinrou.databinding.FragmentVoting9Binding


class Voting9 : Fragment() {
    private lateinit var Suspect9: String
    private lateinit var remainmembers8: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String
    private lateinit var candidate5: String
    private lateinit var candidate6: String
    private lateinit var candidate7: String
    private lateinit var candidate8: String
    private lateinit var candidate9: String
    private var _binding: FragmentVoting9Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting9Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")

        var fake = pref.getStringSet("remainmembers9", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
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

            members.remove(Suspect9)
            remainmembers8 = members.toSet()

            binding.judge.setOnClickListener {
                var pref = PreferenceManager.getDefaultSharedPreferences(context)
                pref.edit {
                    putStringSet("remainmembers8", remainmembers8)
                    putString("Suspect9", Suspect9)
                    if (Suspect9 == jinrouname) {
                        findNavController().navigate(R.id.action_voting9_to_trueresult1)

                    } else {
                        findNavController().navigate(R.id.action_voting9_to_falseresult1)
                    }
                }

            }

        }
        return binding.root
    }
}