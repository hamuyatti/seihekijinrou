package com.example.seihekijinrou.MeetingandVotingandResult

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
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentTrueresult1Binding


class trueresult1 : Fragment() {
    private var _binding: FragmentTrueresult1Binding? = null
    private val binding get()=_binding!!
    private var soundResId = 0
    private lateinit var soundPool: SoundPool

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTrueresult1Binding.inflate(layoutInflater,container,false)

            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            var ThistimeSuspect=pref.getString("ThistimeSuspect","")

            binding.trueplayer.text = "$ThistimeSuspect さんは、、"


            loadingDelay()
            loadingDelay2()
            return binding.root
        }

        override fun onResume() {
            super.onResume()
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
            soundResId = soundPool.load(context, R.raw.yes, 1)


        }

        override fun onPause() {
            super.onPause()
            soundPool.release()
        }

        fun loadingDelay() {
            Handler().postDelayed(
                {
                    findNavController().navigate(R.id.action_trueresult1_to_trueresult2)
                    soundPool.play(soundResId, 1.0f, 1.0f, 0, 0, 1.0f)
                },
                1000,
            )

        }

        fun loadingDelay2() {
            Handler().postDelayed(
                {

                    soundPool.play(soundResId, 1.0f, 1.0f, 0, 0, 1.0f)
                },
                500,
            )

        }
    }

