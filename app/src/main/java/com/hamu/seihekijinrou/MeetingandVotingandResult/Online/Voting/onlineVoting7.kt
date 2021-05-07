package com.hamu.seihekijinrou.MeetingandVotingandResult.Online.Voting

import android.app.AlertDialog
import android.os.Bundle
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
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting7Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting9Binding


class onlineVoting7 :OnlineabstractVoting() {
    private var _binding: FragmentOnlineVoting7Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineVoting7Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loading.visibility = View.INVISIBLE
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var numberofpeople = pref.getString("numberofpeople", "")
        jinrouname = pref.getString("jinrou", "").toString()

        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Voting = collection.document("投票7")

        var jinrouseiheki = pref.getString("jinrouseiheki", "")
        binding.jinrouseiheki.text = "$jinrouseiheki は誰の性癖？？"


        var tmp = pref.getStringSet("remainmembers", setOf(""))
        if (tmp != null) {
            members = tmp.toMutableList()
            pref.edit().remove("remainmembers").apply()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]
            candidate7 = members[6]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Voted = candidate1

                R.id.name2 -> Voted = candidate2

                R.id.name3 -> Voted = candidate3

                R.id.name4 -> Voted = candidate4

                R.id.name5 -> Voted = candidate5

                R.id.name6 -> Voted = candidate6

                R.id.name7 -> Voted = candidate7


            }
            binding.voting.setOnClickListener {
                binding.voting.visibility = View.GONE
                binding.loading.visibility = View.VISIBLE
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

                var list1 = listOf(vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10)

                var vote1count = list1.count { it == candidate1 }
                var vote2count = list1.count { it == candidate2 }
                var vote3count = list1.count { it == candidate3 }
                var vote4count = list1.count { it == candidate4 }
                var vote5count = list1.count { it == candidate5 }
                var vote6count = list1.count { it == candidate6 }
                var vote7count = list1.count { it == candidate7 }

                var list = mutableListOf<votedata>()
                list.add(votedata(candidate1, vote1count))
                list.add(votedata(candidate2, vote2count))
                list.add(votedata(candidate3, vote3count))
                list.add(votedata(candidate4, vote4count))
                list.add(votedata(candidate5, vote5count))
                list.add(votedata(candidate6, vote6count))
                list.add(votedata(candidate7, vote7count))


                var list2 = list.sortedByDescending { it.count }
                pref.edit {
                    putString("ThistimeMeeting", "7")
                }.apply { }

                /*再投票するかを決めます*/
                if (list2[0].count == list2[1].count && list2[1].count == list2[2].count && list2[2].count == list2[3].count
                        && list2[3].count == list2[4].count && list2[4].count == list2[5].count && list2[5].count == list2[6].count) {


                    AlertDialog.Builder(requireContext())
                            .setMessage("同数投票です。")
                            .setPositiveButton("もどる") { dialog, which ->
                            }.show()

                    Voting.delete()
                    binding.voting.visibility = View.VISIBLE
                    binding.loading.visibility = View.INVISIBLE

                } else if (list2[0].count == list2[1].count && list2[1].count == list2[2].count) {
                    var remainmembers = setOf(list2[3].name, list[4].name, list2[5].name, list2[6].name)
                    var Suspectmembers = setOf(list2[0].name, list2[1].name, list2[2].name)

                    var pref = PreferenceManager.getDefaultSharedPreferences(context)
                    pref.edit {
                        putStringSet("remainmembers", remainmembers)
                        putStringSet("Suspectmembers", Suspectmembers)
                    }.apply {}
                    if (Suspectmembers.contains(jinrouname)) {
                        whendisagreeBunContainjinrou()
                    } else {
                        whendisagree()
                    }

                } else if (list2[0].count == list2[1].count) {
                    var remainmembers = setOf(list2[2].name, list2[3].name, list[4].name, list2[5].name, list2[6].name)
                    var Suspectmembers = setOf(list2[0].name, list2[1].name)

                    var pref = PreferenceManager.getDefaultSharedPreferences(context)
                    pref.edit {
                        putStringSet("remainmembers", remainmembers)
                        putStringSet("Suspectmembers", Suspectmembers)
                    }.apply {}
                    if (Suspectmembers.contains(jinrouname)) {
                        whendisagreeBunContainjinrou()
                    } else {
                        whendisagree()
                    }

                } else {
                    Suspect = list2[0].name
                    var remainmembers = setOf(list2[1].name, list2[2].name, list2[3].name, list2[4].name, list2[5].name, list2[6].name)

                    var pref = PreferenceManager.getDefaultSharedPreferences(context)
                    pref.edit {
                        putStringSet("remainmembers", remainmembers)
                    }.apply {}
                    whenOpinionsAreUnited()
                }

            }
        }
    }


        fun whendisagree() {


            findNavController().navigate(R.id.action_onlineVoting7_to_whendisagree)
        }

        fun whendisagreeBunContainjinrou() {


            findNavController().navigate(R.id.action_onlineVoting7_to_whendisagree)


        }

        fun whenOpinionsAreUnited() {

            var bundle = bundleOf("Suspect" to Suspect)

            findNavController().navigate(R.id.action_onlineVoting7_to_whenOpinionsAreUited, bundle)
        }


    }