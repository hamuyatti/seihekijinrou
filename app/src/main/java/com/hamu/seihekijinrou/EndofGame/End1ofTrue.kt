package com.hamu.seihekijinrou.EndofGame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.common.base.Functions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.ActivityEnd1oftrueBinding



class End1ofTrue : AppCompatActivity() {
    private lateinit var binding: ActivityEnd1oftrueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnd1oftrueBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var roomname = pref.getString("roomname","")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")

        /*逐一消した方がいいのでしょうか、、クエリ、削除の仕様の都合上大量になってしまいました*/
        collection.document("参加人数").delete()
        collection.document("会議状況").delete()
        collection.document("名前情報").delete()
        collection.document("性癖情報").delete()
        collection.document("投票1").delete()
        collection.document("投票2").delete()
        collection.document("投票3").delete()
        collection.document("投票4").delete()
        collection.document("投票5").delete()
        collection.document("投票6").delete()
        collection.document("投票7").delete()
        collection.document("投票8").delete()
        collection.document("投票9").delete()
        collection.document("投票10").delete()
        collection.document("名前1").delete()
        collection.document("名前2").delete()
        collection.document("名前3").delete()
        collection.document("名前4").delete()
        collection.document("名前5").delete()
        collection.document("名前6").delete()
        collection.document("名前7").delete()
        collection.document("名前8").delete()
        collection.document("名前9").delete()
        collection.document("名前10").delete()
        collection.document("性癖1").delete()
        collection.document("性癖2").delete()
        collection.document("性癖3").delete()
        collection.document("性癖4").delete()
        collection.document("性癖5").delete()
        collection.document("性癖6").delete()
        collection.document("性癖7").delete()
        collection.document("性癖8").delete()
        collection.document("性癖9").delete()
        collection.document("性癖10").delete()



        var name1 = pref.getString("name1", "").toString()
        var name2 = pref.getString("name2", "").toString()
        var name3 = pref.getString("name3", "").toString()
        var name4 = pref.getString("name4", "").toString()
        var name5 = pref.getString("name5", "").toString()
        var name6 = pref.getString("name6", "").toString()



        binding.button4.setOnClickListener {
            if ((name1.length > 0 || name2.length > 0 || name3.length > 0 || name4.length > 0 || name5.length > 0 )&& name6.length == 0) {
                var intent = Intent(this, End3::class.java)
                startActivity(intent)
            } else {
                var intent = Intent(this, End2::class.java)
                startActivity(intent)
            }
        }
        binding.backtotitle.setOnClickListener {
            var intent = Intent(this, explanation::class.java)
            startActivity(intent)
        }
    }


 }
