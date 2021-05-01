package com.hamu.seihekijinrou.MeetingandVotingandResult.Online.Voting

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
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting2Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding


class onlineVoting2 : OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting2Binding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOnlineVoting2Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var numberofpeople = pref.getString("numberofpeople", "")
        jinrouname = pref.getString("jinrou", "").toString()

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票2")

        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"

        var tmp = pref.getStringSet("remainmembers", setOf(""))
        if (tmp != null) {
            pref.edit().remove("remainmembers").apply()
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Voted = candidate1

                R.id.name2 -> Voted = candidate2

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
        Voting.addSnapshotListener { it, tmp ->

            if (it?.contains("$numberofpeople") == true) {
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
                var vote10 = it!!.data?.get("10")


                var list1 = mutableListOf(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10)

                var vote1count = list1.count { it == candidate1 }
                var vote2count = list1.count { it == candidate2 }


                var list2 = mutableListOf<votedata>()
                list2.add(votedata(candidate1, vote1count))
                list2.add(votedata(candidate2, vote2count))


                var list = list2.sortedByDescending { it.count }



                if (list[0].count == list[1].count ) {
                    var remainmembers = members.toSet()
                    pref.edit {
                        putStringSet("remainmembers", remainmembers)
                    }.apply {}
                    Voting.delete()
                    whensameNumVoting()
                } else {
                    Suspect = list[0].name
                    var remainmembers = setOf(list[1].name)

                    var pref = PreferenceManager.getDefaultSharedPreferences(context)
                    pref.edit {
                        putStringSet("remainmembers", remainmembers)
                        putString("Suspect", Suspect)
                    }.apply { }

                    whenOpinionsAreUnited()
                }
            }
        }
        return binding.root
    }

    fun whensameNumVoting() {
        var bundle = bundleOf("where" to 2)

        findNavController().navigate(R.id.action_onlineVoting2_to_equalvote2, bundle)
    }
    fun whenOpinionsAreUnited() {


        var bundle = bundleOf("Suspect" to Suspect)

        findNavController().navigate(R.id.action_onlineVoting2_to_whenOpinionsAreUited, bundle)
    }
}
