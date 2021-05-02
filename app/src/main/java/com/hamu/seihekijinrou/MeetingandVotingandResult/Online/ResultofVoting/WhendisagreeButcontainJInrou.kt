package com.hamu.seihekijinrou.MeetingandVotingandResult.Online.ResultofVoting

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.EndofGame.End1ofBad
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding
import com.hamu.seihekijinrou.databinding.FragmentWhendisagreeButcontainJInrouBinding


class WhendisagreeButcontainJInrou : Fragment() {
    private var _binding:FragmentWhendisagreeButcontainJInrouBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhendisagreeButcontainJInrouBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var tmp=pref.getStringSet("Suspectmembers", setOf(""))
        var Suspectmembers = tmp?.toList()
        var Suspectmembersize = Suspectmembers?.size

        pref.edit{
            putStringSet("remainmembers",tmp)
        }.apply {  }

        if (Suspectmembersize == 2) {
            var member1 = Suspectmembers?.get(0)
            var member2 = Suspectmembers?.get(1)

            binding.text.text = """
                |$member1
                |$member2
                |が選ばれましたが、
                |人狼が含まれています！
            """.trimMargin()
        } else if (Suspectmembersize == 3) {
            var member1 = Suspectmembers?.get(0)
            var member2 = Suspectmembers?.get(1)
            var member3 = Suspectmembers?.get(2)

            binding.text.text = """
                |$member1
                |$member2
                |$member3
                |が選ばれましたが、
                |人狼が含まれています！
            """.trimMargin()

        } else if (Suspectmembersize == 4) {
            var member1 = Suspectmembers?.get(0)
            var member2 = Suspectmembers?.get(1)
            var member3 = Suspectmembers?.get(2)
            var member4 = Suspectmembers?.get(3)


            binding.text.text = """
                |$member1
                |$member2
                |$member3
                |$member4
                |が選ばれましたが、
                |人狼が含まれています！
            """.trimMargin()

        }else if (Suspectmembersize == 4) {
            var member1 = Suspectmembers?.get(0)
            var member2 = Suspectmembers?.get(1)
            var member3 = Suspectmembers?.get(2)
            var member4 = Suspectmembers?.get(3)
            var member5 = Suspectmembers?.get(4)


            binding.text.text = """
                |$member1
                |$member2
                |$member3
                |$member4
                |$member5
                |が選ばれましたが、
                |人狼が含まれています！
            """.trimMargin()

        }



        Handler().postDelayed(
            {
                    binding.text.text = "再投票です。"
                    Handler().postDelayed(
                        {
                            when (Suspectmembersize) {
                                2 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting2)
                                3 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting3)
                                4 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting4)
                                5 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting5)
                                6 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting6)
                                7 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting7)
                                8 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting8)
                                9 -> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting9)
                                10-> findNavController().navigate(R.id.action_whendisagreeButcontainJInrou_to_onlineVoting10)


                            }

                        },
                        2500
                    )
            },
            3000
        )


        return binding.root
    }


}