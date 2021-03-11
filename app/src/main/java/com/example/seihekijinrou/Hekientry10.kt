package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientry10Binding



class Hekientry10 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientry10Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientry10Binding.inflate(layoutInflater)
        setContentView(binding.root)


/*どの人数の時も1~の名前で管理したいため、人数次第で切り捨てる最初の処理程大きい数字を用いています。*/
        binding.seihekiup10.setOnClickListener {
            var heki = binding.heki10.text
            var hekilength = heki.length
            var name = binding.getname10.text
            var namelength = name.length

            if (hekilength == 0 && namelength == 0) {
                binding.seihekiup10.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.seihekiup10.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.seihekiup10.text = "お名前を教えてください"

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
            putString("seiheki10", binding.heki10.text.toString())
            putString("name10", binding.getname10.text.toString())
                .apply()
        }
    }
}