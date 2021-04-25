package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting10Binding

class OnlineVoting10 : OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting10Binding? = null
    private val binding get() = _binding!!
    private lateinit var Voted: String
    private lateinit var Suspect10: String
    private lateinit var remainmembers9: Set<String>

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
                        }else if (!it.contains("投票5")) {
                            var vote = hashMapOf(
                                    "投票5" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        } else if (!it.contains("投票6")) {
                            var vote = hashMapOf(
                                    "投票6" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        } else if (!it.contains("投票7")) {
                            var vote = hashMapOf(
                                    "投票7" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        }else if (!it.contains("投票8")) {
                            var vote = hashMapOf(
                                    "投票8" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        }else if (!it.contains("投票8")) {
                            var vote = hashMapOf(
                                    "投票8" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        }else if (!it.contains("投票9")) {
                            var vote = hashMapOf(
                                    "投票9" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        }else if (!it.contains("投票10")) {
                            var vote = hashMapOf(
                                    "投票10" to "$Voted"
                            )
                            Voting.set(vote, SetOptions.merge())
                        }
                    }
        }

        Voting.addSnapshotListener { tmp1, tmp2 ->
            Voting
                    .get()
                    .addOnSuccessListener {
                        if (it.contains("投票10")) {
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

                            var list = listOf(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10)

                            var vote1count = list.count { it == vote1 }
                            var vote2count = list.count { it == vote2 }
                            var vote3count = list.count { it == vote3 }
                            var vote4count = list.count { it == vote4 }
                            var vote5count = list.count { it == vote5 }
                            var vote6count = list.count { it == vote6 }
                            var vote7count = list.count { it == vote7 }
                            var vote8count = list.count { it == vote8 }
                            var vote9count = list.count { it == vote9 }
                            var vote10count = list.count { it == vote10 }


                            var list1 = mutableListOf<votedata>()
                            list1.add(votedata(vote1 as String, vote1count))
                            list1.add(votedata(vote2 as String, vote2count))
                            list1.add(votedata(vote3 as String, vote3count))
                            list1.add(votedata(vote4 as String, vote4count))
                            list1.add(votedata(vote5 as String, vote5count))
                            list1.add(votedata(vote6 as String, vote6count))
                            list1.add(votedata(vote7 as String, vote7count))
                            list1.add(votedata(vote8 as String, vote8count))
                            list1.add(votedata(vote9 as String, vote9count))
                            list1.add(votedata(vote10 as String, vote10count))


                            list1.sortByDescending { it.count }

                            if (list1[0].count == list1[1].count) {
                                var Newcandidate = hashMapOf(
                                        "candidate1" to list1[0].name,
                                        "candidate2" to list1[1].name
                                )
                                Voting.set(Newcandidate, SetOptions.merge())
                                pref.edit {
                                    putString("ThistimeMeeting", "10")
                                }
                                findNavController().navigate(R.id.action_onlineVoting4_to_whenOpinionsAreUited)
                            } else if (list1[0].count == list1[1].count || list1[1].count == list1[2].count|| list1[2].count == list1[3].count) {
                                var Newcandidate = hashMapOf(
                                        "candidate1" to list1[0].name,
                                        "candidate2" to list1[1].name,
                                        "candidate3" to list1[2].name
                                )
                                Voting.set(Newcandidate, SetOptions.merge())
                                pref.edit {
                                    putString("ThistimeMeeting", "10")
                                }
                                findNavController().navigate(R.id.action_onlineVoting4_to_whenOpinionsAreUited)

                            }else if (list1[0].count == 1) {
                                var Newcandidate = hashMapOf(
                                        "candidate1" to list1[0].name,
                                        "candidate2" to list1[1].name,
                                        "candidate3" to list1[2].name,
                                        "candidate4" to list1[3].name,
                                        "candidate5" to list1[4].name,
                                        "candidate6" to list1[5].name,
                                        "candidate7" to list1[6].name,
                                        "candidate8" to list1[7].name,
                                        "candidate9" to list1[8].name,
                                        "candidate10" to list1[9].name
                                )
                                Voting.set(Newcandidate, SetOptions.merge())
                                pref.edit {
                                    putString("ThistimeMeeting", "10")
                                }
                                findNavController().navigate(R.id.action_onlineVoting4_to_whenOpinionsAreUited)
                            }else{
                                var Suspect10 = hashMapOf(
                                        "Suspect10" to list1[0].name,

                                        )
                                Voting.set(Suspect10, SetOptions.merge())
                                pref.edit {
                                    putString("ThistimeMeeting", "10")
                                }.apply {  }
                            }


                        }
                    }
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
            findNavController().navigate(R.id.action_onlineVoting10_to_whenOpinionsAreUited)
        } else {
            findNavController().navigate(R.id.action_onlineVoting10_to_whendisagree)
        }
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

    abstract  fun judge()

}