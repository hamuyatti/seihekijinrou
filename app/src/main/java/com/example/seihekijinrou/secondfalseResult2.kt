package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivitySecondfalseResult2Binding

class secondfalseResult2 : AppCompatActivity() {
    private lateinit var binding:ActivitySecondfalseResult2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondfalseResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var Suspect2 = pref.getString("Suspect2","")


     binding.check2.text = "{$Suspect2}さんの性癖を公開しますか？"
     binding.Yes2.setOnClickListener{
        Yesbutton()
    }
    binding.No2.setOnClickListener{
        Nobutton()
    }

}
fun Yesbutton() {
    var intent = Intent(this, SecondOpenSeiheki::class.java)
    startActivity(intent)
}

fun Nobutton() {

    var intent = Intent(this, End1::class.java)
    startActivity(intent)
}
}

