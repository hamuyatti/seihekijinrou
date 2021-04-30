package com.hamu.seihekijinrou.MeetingandVotingandResult.Online.ResultofVoting

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.EndofGame.End1ofBad
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.FragmentWhendisagreeBinding

class Whendisagree : Fragment() {
    private var _binding: FragmentWhendisagreeBinding? = null
    private val binding get() = _binding!!
    private var soundResId1 = 0
    private lateinit var soundPool: SoundPool


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhendisagreeBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)

        var remainmembersize = pref.getStringSet("remainmembers", setOf(""))?.toMutableList()?.size
        var tmp = pref.getStringSet("Suspectmembers", setOf(""))
        var Suspectmember = tmp?.toList()
        var Suspectmembersize = Suspectmember?.size

        soundPool =
                SoundPool.Builder().run {
                    val audioAttributes = AudioAttributes.Builder().run {
                        setUsage(AudioAttributes.USAGE_MEDIA)
                        build()
                    }
                    setMaxStreams(1)
                    setAudioAttributes(audioAttributes)
                    build()
                }
        soundResId1 = soundPool.load(context, R.raw.nott, 1)

        soundPool.play(soundResId1, 1.0f, 1.0f, 0, 0, 1.0f)


        if (Suspectmembersize == 2) {
            var member1 = Suspectmember?.get(0)
            var member2 = Suspectmember?.get(1)

            binding.textView19.text = """
                |$member1
                |$member2
                |が選ばれましたが
                |全員ハズレです、、
            """.trimMargin()
        } else if (Suspectmembersize == 3) {
            var member1 = Suspectmember?.get(0)
            var member2 = Suspectmember?.get(1)
            var member3 = Suspectmember?.get(2)

            binding.textView19.text = """
                |$member1
                |$member2
                |$member3
                |が選ばれましたが
                |全員ハズレです、、
            """.trimMargin()

        } else if (Suspectmembersize == 4) {
            var member1 = Suspectmember?.get(0)
            var member2 = Suspectmember?.get(1)
            var member3 = Suspectmember?.get(2)
            var member4 = Suspectmember?.get(3)


            binding.textView19.text = """
                |$member1
                |$member2
                |$member3
                |$member4
                |が選ばれましたが
                |全員ハズレです、、
            """.trimMargin()

        }


        Handler().postDelayed(
                {
                    if (remainmembersize!! >= 2) {
                        binding.textView19.text = "再投票です。"
                        Handler().postDelayed(
                                {
                                    when (remainmembersize) {
                                        2 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting2)
                                        3 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting3)
                                        4 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting4)
                                        5 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting5)
                                        6 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting6)
                                        7 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting7)
                                        8 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting8)
                                        9 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting9)
                                        10 -> findNavController().navigate(R.id.action_whendisagree_to_onlineVoting10)


                                    }

                                },
                                2500,
                        )
                    } else {
                        binding.textView19.text = "人間側の負けです。"
                        Handler().postDelayed(
                                {
                                    val intent = Intent(context, End1ofBad::class.java)
                                    startActivity(intent)

                                },
                                2500,
                        )
                    }
                },
                3000,
        )



        return binding.root
    }

}











