package com.example.seihekijinrou.ResultofVoting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.EndofGame.End1ofTrue
import com.example.seihekijinrou.databinding.ActivityTrueResult2Binding


class trueResult2 : AppCompatActivity() {
    private lateinit var binding: ActivityTrueResult2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTrueResult2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var ThistimeSuspect = pref.getString("ThistimeSuspect","")
        var jinrou = pref.getString("jinrou","")

        binding.textView9.text = "$ThistimeSuspect さんは性癖「$jinrou」の持ち主です!"



        loadingDelay()

    }

    fun loadingDelay(){
        Handler().postDelayed(
            {
                val intent = Intent(this, End1ofTrue::class.java)
                startActivity(intent)
            },
            2000,
        )
    }
}
