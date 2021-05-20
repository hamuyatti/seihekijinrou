package com.hamu.seihekijinrou.Preparation.Online


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Online.CenterofOnlinegameprocess
import com.hamu.seihekijinrou.databinding.ActivityWaitingentryBinding


class Waitingentry : AppCompatActivity() {
    private lateinit var binding: ActivityWaitingentryBinding
    var db = Firebase.firestore
    lateinit var jinrou: Any
    lateinit var jinrouseiheki: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWaitingentryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        var roomname = pref.getString("roomname", "")
        var collection = db.collection("$roomname")

        var sameroomcheck = db.collection("$roomname").document("$roomname")
        var membernumber = collection.document("参加人数")
        var meetingsituation = collection.document("会議状況")

        membernumber.addSnapshotListener { it, tmp ->
            var nownumberofmenber = it?.data?.size.toString()
            binding.nownumberfmember.text = "$nownumberofmenber 人参加しています。"
        }

        collection
                .whereEqualTo("ゲーム状況", "開始")
                .addSnapshotListener { it, tmp ->
                    if (!it?.isEmpty!!) {
                        var check = pref.getString("check", "")
                        //次へ進むをおした端末での誤作動を防いでいます。
                        if (check?.isEmpty()!!) {
                            membernumber
                                    .get()
                                    .addOnSuccessListener {
                                        var numberofpeople = it.data?.size
                                        collection.document("名前と性癖")
                                                .get()
                                                .addOnSuccessListener {
                                                    var heki1 = it.data?.get("性癖1")
                                                    var heki2 = it.data?.get("性癖2")
                                                    var heki3 = it.data?.get("性癖3")
                                                    var heki4 = it.data?.get("性癖4")
                                                    var heki5 = it.data?.get("性癖5")
                                                    var heki6 = it.data?.get("性癖6")
                                                    var heki7 = it.data?.get("性癖7")
                                                    var heki8 = it.data?.get("性癖8")
                                                    var heki9 = it.data?.get("性癖9")
                                                    var heki10 = it.data?.get("性癖10")

                                                    var name1 = it.data?.get("名前1")
                                                    var name2 = it.data?.get("名前2")
                                                    var name3 = it.data?.get("名前3")
                                                    var name4 = it.data?.get("名前4")
                                                    var name5 = it.data?.get("名前5")
                                                    var name6 = it.data?.get("名前6")
                                                    var name7 = it.data?.get("名前7")
                                                    var name8 = it.data?.get("名前8")
                                                    var name9 = it.data?.get("名前9")
                                                    var name10 = it.data?.get("名前10")

                                                    jinrou = it.data?.get("人狼")!!
                                                    jinrouseiheki = it.data?.get("人狼性癖")!!

                                                    //動作を軽くするために、データは逐次通信を行うのではなく共有プリファレンスで保持することにしました。
                                                    pref.edit {
                                                        putString("name1", name1 as String?);putString("name2", name2 as String?)
                                                        putString("name3", name3 as String?);putString("name4", name4 as String?)
                                                        putString("name5", name5 as String?);putString("name6", name6 as String?)
                                                        putString("name7", name7 as String?);putString("name8", name8 as String?)
                                                        putString("name9", name9 as String?);putString("name10", name10 as String?)
                                                        putString("seiheki1", heki1 as String?);putString("seiheki2", heki2 as String?)
                                                        putString("seiheki3", heki3 as String?);putString("seiheki4", heki4 as String?)
                                                        putString("seiheki5", heki5 as String?);putString("seiheki6", heki6 as String?)
                                                        putString("seiheki7", heki7 as String?);putString("seiheki8", heki8 as String?)
                                                        putString("seiheki9", heki9 as String?);putString("seiheki10", heki10 as String?)
                                                        putString("jinrou", jinrou.toString());putString("jinrouseiheki", jinrouseiheki.toString())
                                                        putString("numberofpeople", numberofpeople.toString())

                                                    }.apply { }

                                                    startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                                                }

                                    }
                        }
                    }
                }



        binding.gotogame.setOnClickListener {
            membernumber
                    .get()
                    .addOnSuccessListener {
                        if (it.data?.size == 1 || it.data?.size == 2) {
                            AlertDialog.Builder(this)
                                    .setMessage("３人必要です。")
                                    .setPositiveButton("戻る") { dialog, which ->
                                    }.show()
                        } else {
                            AlertDialog.Builder(this)
                                    .setMessage("進むとメンバーの変更が出来ません。よろしいですか？")
                                    .setPositiveButton("進む") { dialog, which ->
                                        pref.edit {
                                            putString("check", "確認")
                                        }.apply { }
                                        //参加している人数をカウントしています。人狼を決める時の乱数の上限にします。
                                        var numberofpeople = it.data?.size

                                        //この値をリッスンして待機している人の画面も連動させます。
                                        val gamesituation = hashMapOf(
                                                "ゲーム状況" to "開始"
                                        )

                                        meetingsituation.set(gamesituation)

                                        //もう部屋に入れないようにします。
                                        sameroomcheck.delete()

                                        collection.document("名前と性癖")
                                                .get()
                                                .addOnSuccessListener {
                                                    var heki1 = it.data?.get("性癖1")
                                                    var heki2 = it.data?.get("性癖2")
                                                    var heki3 = it.data?.get("性癖3")
                                                    var heki4 = it.data?.get("性癖4")
                                                    var heki5 = it.data?.get("性癖5")
                                                    var heki6 = it.data?.get("性癖6")
                                                    var heki7 = it.data?.get("性癖7")
                                                    var heki8 = it.data?.get("性癖8")
                                                    var heki9 = it.data?.get("性癖9")
                                                    var heki10 = it.data?.get("性癖10")

                                                    var name1 = it.data?.get("名前1")
                                                    var name2 = it.data?.get("名前2")
                                                    var name3 = it.data?.get("名前3")
                                                    var name4 = it.data?.get("名前4")
                                                    var name5 = it.data?.get("名前5")
                                                    var name6 = it.data?.get("名前6")
                                                    var name7 = it.data?.get("名前7")
                                                    var name8 = it.data?.get("名前8")
                                                    var name9 = it.data?.get("名前9")
                                                    var name10 = it.data?.get("名前10")

                                                    var number = (1..numberofpeople!!).random()

                                                    when (number) {
                                                        1 -> {
                                                            jinrou = heki1!!
                                                            jinrouseiheki = name1!!
                                                        }
                                                        2 -> {
                                                            jinrou = heki2!!
                                                            jinrouseiheki = name2!!
                                                        }
                                                        3 -> {
                                                            jinrou = heki3!!
                                                            jinrouseiheki = name3!!
                                                        }
                                                        4 -> {
                                                            jinrou = heki4!!
                                                            jinrouseiheki = name4!!
                                                        }
                                                        5 -> {
                                                            jinrou = heki5!!
                                                            jinrouseiheki = name5!!
                                                        }
                                                        6 -> {
                                                            jinrou = heki6!!
                                                            jinrouseiheki = name6!!
                                                        }
                                                        7 -> {
                                                            jinrou = heki7!!
                                                            jinrouseiheki = name7!!
                                                        }
                                                        8 -> {
                                                            jinrou = heki8!!
                                                            jinrouseiheki = name8!!
                                                        }
                                                        9 -> {
                                                            jinrou = heki9!!
                                                            jinrouseiheki = name9!!
                                                        }
                                                        10 -> {
                                                            jinrou = heki10!!
                                                            jinrouseiheki = name10!!
                                                        }
                                                    }


                                                    var jinrouinfo = hashMapOf(
                                                            "人狼" to "$jinrou",
                                                            "人狼性癖" to "$jinrouseiheki"
                                                    )
                                                    collection.document("名前と性癖").set(jinrouinfo, SetOptions.merge())

                                                    //動作を軽くするために、データは逐次通信を行うのではなく共有プリファレンスで保持することにしました。
                                                    pref.edit {
                                                        putString("name1", name1 as String?);putString("name2", name2 as String?)
                                                        putString("name3", name3 as String?);putString("name4", name4 as String?)
                                                        putString("name5", name5 as String?);putString("name6", name6 as String?)
                                                        putString("name7", name7 as String?);putString("name8", name8 as String?)
                                                        putString("name9", name9 as String?);putString("name10", name10 as String?)
                                                        putString("seiheki1", heki1 as String?);putString("seiheki2", heki2 as String?)
                                                        putString("seiheki3", heki3 as String?);putString("seiheki4", heki4 as String?)
                                                        putString("seiheki5", heki5 as String?);putString("seiheki6", heki6 as String?)
                                                        putString("seiheki7", heki7 as String?);putString("seiheki8", heki8 as String?)
                                                        putString("seiheki9", heki9 as String?);putString("seiheki10", heki10 as String?)
                                                        putString("jinrou", jinrou.toString());putString("jinrouseiheki", jinrouseiheki.toString())
                                                        putString("numberofpeople", numberofpeople.toString())

                                                    }.apply { }
                                                    startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                                                }
                                    }
                                    .setNegativeButton("もどる") { dialog, which ->

                                    }.show()
                        }

                    }

        }
    }
}






