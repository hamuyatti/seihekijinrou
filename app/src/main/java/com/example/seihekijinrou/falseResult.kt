package com.example.seihekijinrou

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityFalseResultBinding

class falseResult : AppCompatActivity() {
    private lateinit var binding: ActivityFalseResultBinding
    private lateinit var soundPool:SoundPool
    private var soundResId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFalseResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var Suspect = pref.getString("Suspect1", "")
        binding.falseplayer.text = "{$Suspect}さんは、、"

        loadingDelay()
        loadingDelay2()

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
        soundResId = soundPool.load(this, R.raw.nott, 1)


    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

        fun loadingDelay() {
            Handler().postDelayed(
                    {
                        val intent = Intent(this, falseResult2::class.java)
                        startActivity(intent)
                        soundPool.play(soundResId,1.0f,1.0f,0,0,1.0f)
                    },
                    1000,
            )

        }
    fun loadingDelay2() {
        Handler().postDelayed(
                {

                    soundPool.play(soundResId,1.0f,1.0f,0,0,1.0f)
                },
                900,
        )

    }
    }


