package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import com.example.seihekijinrou.databinding.ActivityNumberofpeopleBinding

class numberofpeople : AppCompatActivity() {
    private lateinit var binding: ActivityNumberofpeopleBinding
    private lateinit var item: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNumberofpeopleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinner = parent as? Spinner
                val item = spinner?.selectedItem as? String
                item?.let {
                    if (it.isNotEmpty()) {
                        binding.confirm.text = "$it でよろしいですか？"
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        binding.gamestart.setOnClickListener {
            getselectednumber()
        }
    }


    fun getselectednumber() {


        var selectednumber = if (item == "３人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "４人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "５人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "６人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "７人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "８人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (item == "９人") {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {


        }
    }
}

