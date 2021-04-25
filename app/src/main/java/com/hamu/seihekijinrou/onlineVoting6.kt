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
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting6Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting7Binding
import com.hamu.seihekijinrou.databinding.FragmentVoting6Binding

class onlineVoting6 : OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting6Binding? = null
    private val binding get() = _binding!!
    private lateinit var Voted: String
    private lateinit var Suspect6: String
    private lateinit var remainmembers5: Set<String>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting6Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票6")

        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers6", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6


        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Voted= candidate1

                R.id.name2 ->Voted = candidate2

                R.id.name3 -> Voted = candidate3

                R.id.name4 -> Voted = candidate4

                R.id.name5 -> Voted = candidate5

                R.id.name6 -> Voted = candidate6


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
                            } else if (!it.contains("投票5")) {
                                var vote = hashMapOf(
                                        "投票5" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())
                            } else if (!it.contains("投票6")) {
                                var vote = hashMapOf(
                                        "投票6" to "$Voted"
                                )
                                Voting.set(vote, SetOptions.merge())

                            }
                        }
            }

            Voting.addSnapshotListener { tmp1, tmp2 ->
                Voting
                        .get()
                        .addOnSuccessListener {
                            if (it.contains("投票6")) {
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

                                var list = listOf(vote1, vote2, vote3, vote4, vote5, vote6)

                                var vote1count = list.count { it == vote1 }
                                var vote2count = list.count { it == vote2 }
                                var vote3count = list.count { it == vote3 }
                                var vote4count = list.count { it == vote4 }
                                var vote5count = list.count { it == vote5 }
                                var vote6count = list.count { it == vote6 }

                                var list1 = mutableListOf<votedata>()
                                list1.add(votedata(vote1 as String, vote1count))
                                list1.add(votedata(vote2 as String, vote2count))
                                list1.add(votedata(vote3 as String, vote3count))
                                list1.add(votedata(vote4 as String, vote4count))
                                list1.add(votedata(vote5 as String, vote5count))
                                list1.add(votedata(vote6 as String, vote6count))




                                list1.sortByDescending { it.count }
                                /* 同列一位を探します。

                                 */
                                if (list1[0].count == list1[1].count ) {
                                    /*3票*/
                                    var Newcandidate = hashMapOf(
                                            "candidate1" to list1[0].name,
                                            "candidate2" to list1[1].name
                                    )
                                    Voting.set(Newcandidate, SetOptions.merge())
                                    pref.edit {
                                        putString("ThistimeMeeting", "6")
                                    }
                                    findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)

                                } else if (list1[0].count == list1[1].count || list1[1].count == list1[2].count || list1[1].count == list1[2].count ) {
                                    /*2票*/
                                    var Newcandidate = hashMapOf(
                                            "candidate1" to list1[0].name,
                                            "candidate2" to list1[1].name,
                                            "candidate3" to list1[2].name,
                                    )
                                    Voting.set(Newcandidate, SetOptions.merge())
                                    pref.edit {
                                        putString("ThistimeMeeting", "6")
                                    }
                                    findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)
                                } else if (list1[0].count == 1) {
                                    /*1票*/
                                    var Newcandidate = hashMapOf(
                                            "candidate1" to list1[0].name,
                                            "candidate2" to list1[1].name,
                                            "candidate3" to list1[2].name,
                                            "candidate4" to list1[3].name,
                                            "candidate5" to list1[4].name,
                                            "candidate6" to list1[5].name

                                            )
                                    Voting.set(Newcandidate, SetOptions.merge())
                                    pref.edit {
                                        putString("ThistimeMeeting", "6")
                                    }
                                    findNavController().navigate(R.id.action_onlineVoting4_to_whendisagree)
                                } else{
                                    var Suspec6= hashMapOf(
                                            "Suspect6" to list1[0].name,

                                            )
                                    Voting.set(Suspect6, SetOptions.merge())
                                    pref.edit {
                                        putString("ThistimeMeeting", "6")
                                    }.apply {  }
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
            putStringSet("remainmembers5", remainmembers5)
            putString("Suspect6", Suspect6)
            putString("ThistimeSuspect",Suspect6)
        }.apply { }
        if (Suspect6 == jinrouname) {

            findNavController().navigate(R.id.action_voting6_to_trueresult1)

        } else {

            findNavController().navigate(R.id.action_voting6_to_falseresult1)
        }
    }
}
