package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Voting.abstractVoting
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting10Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting4Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting4Binding

class onlineVoting4 : OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting4Binding? = null
    private val binding get() = _binding!!

    private lateinit var Voted: String
    private lateinit var Suspect4: Any
    private lateinit var remainmembers3: Set<String>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting4Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票4")

        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"

        var tmp = pref.getStringSet("remainmembers4", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
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
                R.id.name1 -> Voted = candidate1

                R.id.name2 -> Voted = candidate2

                R.id.name3 -> Voted = candidate3

                R.id.name4 -> Voted = candidate4

            }

            binding.voting.setOnClickListener {
                Voting
                        .get()
                        .addOnSuccessListener {
                            if (!it.contains("投票1")) {
                                var vote = hashMapOf(
                                        "投票1" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("投票2")) {
                                var vote = hashMapOf(
                                        "投票2" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("投票3")) {
                                var vote = hashMapOf(
                                        "投票3" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("投票4")) {
                                var vote = hashMapOf(
                                        "投票4" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            }
                        }
            }
            Voting.addSnapshotListener { tmp1, tmp2 ->
                Voting
                        .get()
                        .addOnSuccessListener {
                            if (it.contains("投票4")) {
                                data class votedata(
                                        val name: String?,
                                        val count: Int?
                                )


                                var vote1 = it!!.data?.get("投票1")
                                var vote2 = it!!.data?.get("投票2")
                                var vote3 = it!!.data?.get("投票3")
                                var vote4 = it!!.data?.get("投票4")

                                var list1 = listOf(vote1, vote2, vote3, vote4)

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
                                    putString("ThistimeMeeting", "4")
                                }.apply { }



                                if (list2[0].count == list2[1].count && list2[1].count == list2[2].count && list2[2].count == list2[3].count) {
                                    var Newcandidate = hashMapOf(
                                            "candidate1" to candidate1,
                                            "candidate2" to candidate2,
                                            "candidate3" to candidate3,
                                            "candidate4" to candidate4
                                    )
                                    Voting.set(Newcandidate, SetOptions.merge())

                                    findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)
                                } else if (list2[0].count == list2[1].count) {
                                    var Newcandidate = hashMapOf(
                                            "candidate1" to list2[0].name,
                                            "candidate2" to list2[1].name
                                    )
                                    Voting.set(Newcandidate, SetOptions.merge())

                                    findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)
                                } else {
                                    var Suspect = hashMapOf(
                                            "Suspect4" to list2[0].name
                                    )
                                    Voting.set(Suspect, SetOptions.merge())

                                    findNavController().navigate(R.id.action_onlineVoting4_to_whenOpinionsAreUited)
                                }
                            }
                        }
            }

        }

        return binding.root
    }


    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers3", remainmembers3)
        }.apply { }
        if (Suspect4 == jinrouname) {


        } else {

            findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)

        }


    }


}

