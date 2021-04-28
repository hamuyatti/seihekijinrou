package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Voting.abstractVoting
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting4Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting5Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting3Binding

class onlineVoting3 : OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting3Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting3Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var numberofpeople = pref.getString("numberofpeople","")

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票5")

        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers", setOf(""))
        if (tmp != null) {
            pref.edit().remove("remainmembers")
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
                R.id.name1 -> Voted = candidate1

                R.id.name2 -> Voted = candidate2

                R.id.name3 -> Voted = candidate3


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
        }
            Voting.addSnapshotListener { it, tmp2 ->
                if (it?.contains("$numberofpeople")==true) {
                    data class votedata(
                        val name: String,
                        val count: Int
                    )

                    var vote1 = it!!.data?.get("投票1")
                    var vote2 = it!!.data?.get("投票2")
                    var vote3 = it!!.data?.get("投票3")
                    var vote4 = it!!.data?.get("投票4")
                    var vote5 = it!!.data?.get("投票5")
                    var vote6 = it!!.data?.get("投票6")
                    var vote7 = it!!.data?.get("投票7")
                    var vote8 = it!!.data?.get("投票8")
                    var vote9 = it!!.data?.get("投票9")
                    var vote10 = it!!.data?.get("投票10")

                    var list1 = listOf(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10)

                                var vote1count = list1.count { it == candidate1 }
                                var vote2count = list1.count { it == candidate2 }
                                var vote3count = list1.count { it == candidate3 }
                                var vote4count = list1.count { it == candidate4 }

                                var list = mutableListOf<votedata>()
                                list.add(votedata(candidate1, vote1count))
                                list.add(votedata(candidate2, vote2count))
                                list.add(votedata(candidate3, vote3count))
                                list.add(votedata(candidate4, vote4count))

                                var list2 = list.sortedByDescending { it.count }

                                pref.edit {
                                    putString("ThistimeMeeting", "3")
                                }.apply { }



                                if (list2[0].count == list2[1].count && list2[1].count == list2[2].count && list2[2].count == list2[3].count) {
                                    remainmembers = members.toSet()
                                    whensameNumVoting()
                                } else if (list2[0].count == list2[1].count) {
                                    remainmembers= setOf(list2[2].name)
                                    Suspectmembers = setOf(list2[0].name, list2[1].name)
                                    /*引数*/
                                    if(Suspectmembers.contains(jinrouname)){
                                        whendisagreeBunContainjinrou()
                                    }else {
                                        whendisagree()
                                    }
                                } else {
                                    Suspect = list2[0].name
                                    remainmembers  = setOf(list2[1].name,list2[2].name)
                                    /*引数*/
                                    whenOpinionsAreUnited()

                                }
                            }

        }

        return binding.root
    }
    fun whensameNumVoting() {
        var bundle = bundleOf("where" to 3)

        findNavController().navigate(R.id.action_onlineVoting3_to_equalvote2,bundle)
    }
    fun whendisagree(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putStringSet("Suspectmembers",Suspectmembers)
        }.apply {}

        findNavController().navigate(R.id.action_onlineVoting3_to_whendisagree)
    }

    fun whendisagreeBunContainjinrou(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putStringSet("Suspectmembers",Suspectmembers)
        }.apply {}

        findNavController().navigate(R.id.action_onlineVoting3_to_whendisagree)


    }

    fun whenOpinionsAreUnited(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putStringSet("remainmembers", remainmembers)
            putString("Suspect",Suspect)
        }.apply { }

        var bundle = bundleOf("Suspect" to Suspect)

        findNavController().navigate(R.id.action_onlineVoting3_to_whenOpinionsAreUited,bundle,)
    }


}
