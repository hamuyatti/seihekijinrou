package com.example.seihekijinrou.ResultofVoting

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.ActivityTrueResultBinding

class trueResult : AppCompatActivity() {
    private lateinit var binding: ActivityTrueResultBinding
    private lateinit var soundPool: SoundPool
    private var soundResId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrueResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var Suspect10= pref.getString("Suspect10","") as String
        var Suspect9 =  pref.getString("Suspect9","") as String
        var Suspect8 =  pref.getString("Suspect8","") as String
        var Suspect7 =  pref.getString("Suspect7","") as String
        var Suspect6 =  pref.getString("Suspect6","") as String
        var Suspect5 =  pref.getString("Suspect5","") as String
        var Suspect4 =  pref.getString("Suspect4","") as String
        var Suspect3 =  pref.getString("Suspect3","") as String

        var ThistimeSuspect=if(Suspect10.isNotEmpty()&&Suspect9.length ==0){
            Suspect10
        }else if(Suspect9.isNotEmpty()&&Suspect8.length==0){
            Suspect9
        }else if(Suspect8.isNotEmpty()&&Suspect7.length==0){
            Suspect8
        }else if(Suspect7.isNotEmpty()&&Suspect6.length==0){
            Suspect7
        }else if(Suspect6.isNotEmpty()&&Suspect5.length==0){
            Suspect6
        }else if(Suspect5.isNotEmpty()&&Suspect4.length==0){
            Suspect5
        }else if(Suspect4.isNotEmpty()&&Suspect3.length==0){
            Suspect4
        }else {
            Suspect3
        }



        binding.trueplayer.text = "$ThistimeSuspect さんは、、"

        pref.edit { putString("ThistimeSuspect",ThistimeSuspect) }
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
        soundResId = soundPool.load(this, R.raw.yes, 1)


    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    fun loadingDelay() {
        Handler().postDelayed(
                {
                    val intent = Intent(this, trueResult2::class.java)
                    startActivity(intent)
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

