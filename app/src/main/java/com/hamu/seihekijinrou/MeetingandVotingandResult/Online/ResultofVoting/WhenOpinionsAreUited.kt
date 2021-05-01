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
import com.hamu.seihekijinrou.EndofGame.End1ofBad
import com.hamu.seihekijinrou.EndofGame.End1ofTrue
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting4Binding
import com.hamu.seihekijinrou.databinding.FragmentWhenOpinionsAreUitedBinding

class WhenOpinionsAreUited : Fragment() {
    private var _binding: FragmentWhenOpinionsAreUitedBinding? = null
    private val binding get() = _binding!!
    private var soundResId1 = 0
    private var soundResId2 = 0
    private lateinit var soundPool: SoundPool


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentWhenOpinionsAreUitedBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou","")
        var jinrouseiheki = pref.getString("jinrouseiheki","")

        var remainmemberssize =pref.getStringSet("remainmembers", setOf(""))?.size

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
        soundResId1 = soundPool.load(context, R.raw.yes, 1)
        soundResId2 = soundPool.load(context, R.raw.nott, 1)



        var Suspect = arguments?.get("Suspect")as String?


            binding.text.text = "$Suspect さんは、、"
            Handler().postDelayed(
                    {
                        if (Suspect == jinrou) {
                            binding.text.text = "性癖「$jinrouseiheki」の持ち主です！"
                            soundPool.play(soundResId1, 1.0f, 1.0f, 0, 0, 1.0f)
                            Handler().postDelayed(
                                    {
                                        startActivity(Intent(context, End1ofTrue::class.java))
                                    },
                                    1500,
                            )
                        } else {
                            Handler().postDelayed(
                                    {
                                        binding.text.text = "人狼ではありません！"
                                        soundPool.play(soundResId2, 1.0f, 1.0f, 0, 0, 1.0f)
                                        Handler().postDelayed(
                                                { if (remainmemberssize != null) {
                                                    if (remainmemberssize >= 2) {
                                                        findNavController().navigate(R.id.action_whenOpinionsAreUited_to_onlineMeetingtime)
                                                    } else {
                                                        startActivity(Intent(context, End1ofBad::class.java))
                                                    }
                                                }
                                                },
                                                1500,


                                                )
                                    },
                                    1500,
                            )
                        }

                    },1500)
        return binding.root
    }





}