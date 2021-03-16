package com.example.seihekijinrou.Preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientry9Binding


class Hekientry9 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientry9Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientry9Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var firstname = pref.getString("name10", "").toString()

        binding.Seihekiup.setOnClickListener {
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.toString()
            var namelength = name.length

            if (firstname == name) {
                binding.Seihekiup.text = "違う名前を使ってください"
            } else if (hekilength == 0 && namelength == 0) {
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.Seihekiup.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.Seihekiup.text = "お名前を教えてください"
            } else {
                onSeihekiUpTapped(it)
            }


        }
    }

    fun onSeihekiUpTapped(view: View?) {
        var intent = Intent(this, Hekientry8::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putString("seiheki9", binding.Heki.text.toString())
            putString("name9", binding.Getname.text.toString())
                .apply()
        }
    }
}