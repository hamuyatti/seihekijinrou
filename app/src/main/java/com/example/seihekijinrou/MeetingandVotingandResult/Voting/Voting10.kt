package com.example.seihekijinrou.MeetingandVotingandResult.Voting
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

class Voting10 : abstractVoting() {
    private var _binding:FragmentVoting10Binding? = null
    private val binding get() = _binding!!
    private lateinit var Suspect10:String
    private lateinit var remainmembers9:Set<String>
    lateinit var jinrouname:String
    lateinit var members:MutableList<String?>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        _binding = FragmentVoting10Binding.inflate(inflater, container, false)

        candidate1 = pref.getString("name1", "").toString()
        candidate2 = pref.getString("name2", "").toString()
        candidate3 = pref.getString("name3", "").toString()
        candidate4 = pref.getString("name4", "").toString()
        candidate5 = pref.getString("name5", "").toString()
        candidate6 = pref.getString("name6", "").toString()
        candidate7 = pref.getString("name7", "").toString()
        candidate8 = pref.getString("name8", "").toString()
        candidate9 = pref.getString("name9", "").toString()
        candidate10 =pref.getString("name10", "").toString()

        binding.name10.text =candidate10
        binding.name9.text = candidate9
        binding.name8.text = candidate8
        binding.name7.text = candidate7
        binding.name6.text = candidate6
        binding.name5.text = candidate5
        binding.name4.text = candidate4
        binding.name3.text = candidate3
        binding.name2.text = candidate2
        binding.name1.text = candidate1

        jinrouname = pref.getString("jinrouname", "").toString()
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text ="$jinrou は誰の性癖？？"

        members = mutableListOf(candidate1,candidate2,candidate3,candidate4,candidate5,candidate6,candidate7,candidate8,candidate9,candidate10)



        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect10 = candidate1
                R.id.name2 -> Suspect10 = candidate2
                R.id.name3 -> Suspect10 = candidate3
                R.id.name4 -> Suspect10 = candidate4
                R.id.name5 -> Suspect10 = candidate5
                R.id.name6 -> Suspect10 = candidate6
                R.id.name7 -> Suspect10 = candidate7
                R.id.name8 -> Suspect10 = candidate8
                R.id.name9 -> Suspect10 = candidate9
                R.id.name10 ->Suspect10 = candidate10


            }

        }
        binding.judge.setOnClickListener {
         members.remove(Suspect10)
         remainmembers9 = members.toSet() as Set<String>
            judge()
        }

        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
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
abstract class abstractVoting: Fragment() {

   lateinit var candidate1:String
   lateinit var candidate2:String
   lateinit var candidate3:String
   lateinit var candidate4:String
   lateinit var candidate5:String
   lateinit var candidate6:String
   lateinit var candidate7:String
   lateinit var candidate8:String
   lateinit var candidate9:String
   lateinit var candidate10:String
   abstract  fun judge()


}