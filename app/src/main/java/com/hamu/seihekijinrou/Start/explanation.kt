package com.hamu.seihekijinrou.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.core.view.isVisible
import com.hamu.seihekijinrou.Preparation.Online.MakingorEnterroom
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.ActivityExplanationBinding
import kotlin.properties.Delegates

class explanation : AppCompatActivity() {
    private lateinit var binding: ActivityExplanationBinding
    lateinit var tmp:String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExplanationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            clear()
                .apply()
        }

        binding.start.setOnClickListener {
            tmp ="オフライン"
            /*binding.start.visibility = View.INVISIBLE
            binding.button.visibility = View.INVISIBLE
             */
            val strList = arrayOf("この携帯で皆と遊ぶ","対面で複数の携帯を使う","オンラインで通話して遊ぶ")
            AlertDialog.Builder(this)
                    .setTitle("遊び方")
                    .setSingleChoiceItems(strList, 0) { dialog, which:Int ->
                        when(which) {
                            0->tmp = "オフライン"
                            1->tmp = "オフライン（複数端末）"
                            2->tmp = "オンライン"
                        }
                    }
                    .setPositiveButton("進む") { dialog, which: Int ->
                        when (tmp) {
                            "オフライン" -> startActivity(Intent(this, numberofpeople::class.java))
                            "オフライン（複数端末）" -> startActivity(Intent(this, MakingorEnterroom::class.java))
                            "オンライン" -> startActivity(Intent(this, MakingorEnterroom::class.java))
                                    }
                        }
                    .show()
        }
        binding.checkrules.setOnClickListener {
            var intent = Intent(this, checkrules::class.java)
            startActivity(intent)
        }
            binding.button.setOnClickListener {
            var intent = Intent(this, PastSeiheki::class.java)
            startActivity(intent)
        }



    }
}