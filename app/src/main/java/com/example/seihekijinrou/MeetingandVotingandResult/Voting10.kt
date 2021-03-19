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
import com.example.seihekijinrou.databinding.FragmentVoting10Binding

class Voting10 : Fragment() {
    private var _binding:FragmentVoting10Binding? = null
    private val binding get() = _binding!!
    private lateinit var Suspect10:String
    private lateinit var remainmembers9:Set<String>
    private lateinit var members:MutableList<String?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting10Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")


        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")
        var candidate5 = pref.getString("name5", "")
        var candidate6 = pref.getString("name6", "")
        var candidate7 = pref.getString("name7", "")
        var candidate8 = pref.getString("name8", "")
        var candidate9 = pref.getString("name9", "")
        var candidate10 =pref.getString("name10", "")

        binding.name10.text =candidate10.toString()
        binding.name9.text = candidate9.toString()
        binding.name8.text = candidate8.toString()
        binding.name7.text = candidate7.toString()
        binding.name6.text = candidate6.toString()
        binding.name5.text = candidate5.toString()
        binding.name4.text = candidate4.toString()
        binding.name3.text = candidate3.toString()
        binding.name2.text = candidate2.toString()
        binding.name1.text = candidate1.toString()


        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text ="$jinrou は誰の性癖？？"

        members = mutableListOf(candidate1,candidate2,candidate3,candidate4,candidate5,candidate6,candidate7,candidate8,candidate9,candidate10)



        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect10 = candidate1.toString()
                R.id.name2 -> Suspect10 = candidate2.toString()
                R.id.name3 -> Suspect10 = candidate3.toString()
                R.id.name4 -> Suspect10 = candidate4.toString()
                R.id.name5 -> Suspect10 = candidate5.toString()
                R.id.name6 -> Suspect10 = candidate6.toString()
                R.id.name7 -> Suspect10 = candidate7.toString()
                R.id.name8 -> Suspect10 = candidate8.toString()
                R.id.name9 -> Suspect10 = candidate9.toString()
                R.id.name10 -> Suspect10 = candidate10.toString()


            }
            remainmembers9;members.remove(Suspect10)

            binding.judge.setOnClickListener {
                pref.edit {
                    putStringSet("remainmembers9", remainmembers9)
                    putString("Suspect10", Suspect10)
                    putString("ThistimeSuspect",Suspect10)
                }.apply {  }
                if (Suspect10 == jinrouname) {
                    findNavController().navigate(R.id.action_voting10_to_trueresult1)



                } else {
                    findNavController().navigate(R.id.action_voting10_to_falseresult1)

                }
            }

        }

        return binding.root
    }
}