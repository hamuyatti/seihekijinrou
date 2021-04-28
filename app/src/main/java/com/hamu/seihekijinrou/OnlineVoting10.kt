package com.hamu.seihekijinrou

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting10Binding

class OnlineVoting10 :OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting10Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票10")

        jinrouname = pref.getString("jinrouname", "").toString()
        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"

        _binding = FragmentOnlineVoting10Binding.inflate(inflater, container, false)

        candidate1 = pref.getString("name1", "").toString()
        candidate2 = pref.getString("name2", "").toString()
        candidate3 = pref.getString("name3", "").toString()
        candidate4 = pref.getString("name4", "").toString()
        candidate5 = pref.getString("name5", "").toString()
        candidate6 = pref.getString("name6", "").toString()
        candidate7 = pref.getString("name7", "").toString()
        candidate8 = pref.getString("name8", "").toString()
        candidate9 = pref.getString("name9", "").toString()
        candidate10 = pref.getString("name10", "").toString()

        binding.name10.text = candidate10
        binding.name9.text = candidate9
        binding.name8.text = candidate8
        binding.name7.text = candidate7
        binding.name6.text = candidate6
        binding.name5.text = candidate5
        binding.name4.text = candidate4
        binding.name3.text = candidate3
        binding.name2.text = candidate2
        binding.name1.text = candidate1



        members = mutableListOf(
                candidate1,
                candidate2,
                candidate3,
                candidate4,
                candidate5,
                candidate6,
                candidate7,
                candidate8,
                candidate9,
                candidate10
        )



        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Voted = candidate1
                R.id.name2 -> Voted = candidate2
                R.id.name3 -> Voted = candidate3
                R.id.name4 -> Voted = candidate4
                R.id.name5 -> Voted = candidate5
                R.id.name6 -> Voted = candidate6
                R.id.name7 -> Voted = candidate7
                R.id.name8 -> Voted = candidate8
                R.id.name9 -> Voted = candidate9
                R.id.name10 -> Voted = candidate10

            }
            binding.voting.setOnClickListener {
                Voting
                        .get()
                        .addOnSuccessListener {
                            if (!it.contains("1")) {
                                var vote = hashMapOf(
                                        "1" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("2")) {
                                var vote = hashMapOf(
                                        "2" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("3")) {
                                var vote = hashMapOf(
                                        "3" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("4")) {
                                var vote = hashMapOf(
                                        "4" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("5")) {
                                var vote = hashMapOf(
                                        "5" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("6")) {
                                var vote = hashMapOf(
                                        "6" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("7")) {
                                var vote = hashMapOf(
                                        "7" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("8")) {
                                var vote = hashMapOf(
                                        "8" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("8")) {
                                var vote = hashMapOf(
                                        "8" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("9")) {
                                var vote = hashMapOf(
                                        "9" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("10")) {
                                var vote = hashMapOf(
                                        "10" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            }
                        }
            }


        Voting.addSnapshotListener { it, tmp2 ->
            if (it?.contains("10")==true) {
                            data class votedata(
                                    val name: String,
                                    val count: Int
                            )

                            var vote1 = it!!.data?.get("1")
                            var vote2 = it!!.data?.get("2")
                            var vote3 = it!!.data?.get("3")
                            var vote4 = it!!.data?.get("4")
                            var vote5 = it!!.data?.get("5")
                            var vote6 = it!!.data?.get("6")
                            var vote7 = it!!.data?.get("7")
                            var vote8 = it!!.data?.get("8")
                            var vote9 = it!!.data?.get("9")
                            var vote10 =it!!.data?.get("10")

                            var list1 = listOf(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10)

                            var vote1count = list1.count { it == candidate1 }
                            var vote2count = list1.count { it == candidate2 }
                            var vote3count = list1.count { it == candidate3 }
                            var vote4count = list1.count { it == candidate4 }
                            var vote5count = list1.count { it == candidate5 }
                            var vote6count = list1.count { it == candidate6 }
                            var vote7count = list1.count { it == candidate7 }
                            var vote8count = list1.count { it == candidate8 }
                            var vote9count = list1.count { it == candidate9 }
                            var vote10count =list1.count { it == candidate10}


                            var list = mutableListOf<votedata>()
                            list.add(votedata(candidate1, vote1count))
                            list.add(votedata(candidate2, vote2count))
                            list.add(votedata(candidate3, vote3count))
                            list.add(votedata(candidate4, vote4count))
                            list.add(votedata(candidate5, vote5count))
                            list.add(votedata(candidate6, vote6count))
                            list.add(votedata(candidate7, vote7count))
                            list.add(votedata(candidate8, vote8count))
                            list.add(votedata(candidate9, vote9count))
                            list.add(votedata(candidate10, vote10count))


                            var list2 = list.sortedByDescending { it.count }

                            pref.edit {
                                putString("ThistimeMeeting", "10")
                            }.apply { }

                            /*再投票するかを決めます*/
                            if (list2[0].count== list2[1].count && list2[1].count == list2[2].count && list2[2].count == list2[3].count
                                    &&list2[3].count== list2[4].count && list2[4].count == list2[5].count && list2[5].count == list2[6].count
                                    &&list2[6].count== list2[7].count && list2[7].count == list2[8].count && list2[8].count == list2[9].count){
                                        /*全員に一票*/
                                 remainmembers = members.toSet()
                                 whensameNumVoting()

                            } else if (list2[0].count == list2[1].count&&list2[1].count == list2[2].count
                                    &&list2[2].count == list2[3].count&&list2[3].count == list2[4].count) {
                                remainmembers = setOf(list2[5].name,list2[6].name,list2[7].name,list2[8].name,list[9].name)
                                Suspectmembers = setOf(list2[0].name, list2[1].name,list2[2].name,list2[3].name,list[4].name)
                                if(Suspectmembers.contains(jinrouname)){
                                    whendisagreeBunContainjinrou()
                                }else {
                                    whendisagree()
                                }

                            } else if(list2[0].count == list2[1].count&&list2[1].count == list2[2].count){
                                remainmembers =setOf(list2[3].name,list2[4].name,list2[5].name,list2[6].name,list2[7].name,list2[8].name,list[9].name)
                                Suspectmembers = setOf(list2[0].name, list2[1].name,list2[2].name)
                                if(Suspectmembers.contains(jinrouname)){
                                    whendisagreeBunContainjinrou()
                                }else {
                                    whendisagree()
                                }

                            }else if(list2[0].count == list2[1].count) {
                                remainmembers =setOf(list2[2].name,list2[3].name,list2[4].name,list2[5].name,list2[6].name,list2[7].name,list2[8].name,list[9].name)
                                Suspectmembers = setOf(list2[0].name, list2[1].name)
                                if(Suspectmembers.contains(jinrouname)){
                                    whendisagreeBunContainjinrou()
                                }else {
                                    whendisagree()
                                }
                            }else{
                                Suspect = list2[0].name
                                remainmembers = setOf(list2[1].name,list2[2].name,list2[3].name,list2[4].name,list2[5].name,list2[6].name,list2[7].name,list2[8].name,list[9].name)
                                whenOpinionsAreUnited()
                            }
                        }
                    }
        }

        return binding.root
    }

    fun whensameNumVoting() {
        var bundle = bundleOf("where" to 5)

        findNavController().navigate(R.id.action_onlineVoting10_to_equalvote2,bundle)
    }
    fun whendisagree(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putStringSet("Suspectmembers",Suspectmembers)
        }.apply {}

        findNavController().navigate(R.id.action_onlineVoting10_to_whendisagree)
    }

    fun whendisagreeBunContainjinrou(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putStringSet("Suspectmembers",Suspectmembers)
        }.apply {}

        findNavController().navigate(R.id.action_onlineVoting10_to_whendisagree)


    }

    fun whenOpinionsAreUnited(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putString("Suspect",Suspect)
        }.apply { }

        var bundle = bundleOf("Suspect" to Suspect)

        findNavController().navigate(R.id.action_onlineVoting10_to_whenOpinionsAreUited,bundle,)
    }


}

abstract class OnlineabstractVoting: Fragment() {

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

    lateinit var jinrouname:String
    lateinit var members:MutableList<String>
    lateinit var Voted: String
    lateinit var Suspect: String
    lateinit var Suspectmembers:Set<String>
    lateinit var remainmembers: Set<String>

}