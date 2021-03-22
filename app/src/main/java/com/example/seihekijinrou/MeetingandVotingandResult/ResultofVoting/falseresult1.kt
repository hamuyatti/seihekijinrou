package com.example.seihekijinrou.MeetingandVotingandResult.ResultofVoting

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
import com.example.seihekijinrou.databinding.FragmentFalseresult1Binding


class falseresult1 : Fragment() {
    private var _binding: FragmentFalseresult1Binding? = null
    private val binding get() = _binding!!
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFalseresult1Binding.inflate(inflater, container, false)



        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var ThistimeSuspect =pref.getString("ThistimeSuspect","")


        binding.falseplayer.text = "$ThistimeSuspect さんは、、"

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
        soundResId = soundPool.load(context, R.raw.nott, 1)


    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    fun loadingDelay() {
        Handler().postDelayed(
            {
                soundPool.play(soundResId,1.0f,1.0f,0,0,1.0f)

            },
            900,
        )

    }
    fun loadingDelay2() {
        Handler().postDelayed(
            {

                findNavController().navigate(R.id.action_falseresult1_to_falseresult2)
            },
            1000,
        )

    }
}
