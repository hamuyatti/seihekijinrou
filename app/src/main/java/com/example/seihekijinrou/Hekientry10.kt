package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientry10Binding
import com.example.seihekijinrou.databinding.ActivityHekientry5Binding


class Hekientry10 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientry5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientry5Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.Seihekiup.setOnClickListener {
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text
            var namelength = name.length

            if (hekilength == 0 && namelength == 0) {
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.Seihekiup.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.Seihekiup.text = "お名前を教えてください"

            } else if (hekilength >= 1 && namelength >= 1) {
                onSeihekiUpTapped(it)
            } else {
                onSeihekiUpTapped(it)
            }
        }

    }

    fun onSeihekiUpTapped(view: View?) {
        var intent = Intent(this, Hekientry9::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putString("seiheki10", binding.Heki.text.toString())
            putString("name10", binding.Getname.text.toString())
                .apply()
        }
    }
}