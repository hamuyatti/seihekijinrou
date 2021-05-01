package com.hamu.seihekijinrou.Preparation.Online

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.MeetingandVotingandResult.Online.CenterofOnlinegameprocess
import com.hamu.seihekijinrou.databinding.ActivityWaitingentryBinding
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import java.util.jar.Manifest

class Waitingentry : AppCompatActivity() {
    private lateinit var binding: ActivityWaitingentryBinding
    var db = Firebase.firestore


    /* private var mRtcEngine: RtcEngine? = null
    private val mRtcEventHandler = object : IRtcEngineEventHandler()

   */

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWaitingentryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        var roomname = pref.getString("roomname", "")
        var collection = db.collection("$roomname")

        var sameroomcheck = db.collection("$roomname").document("$roomname")
        var membernumber =collection.document("参加人数")
        var meetingsituation= collection.document("会議状況")

        membernumber.addSnapshotListener { it, tmp ->
            var nownumberofmenber = it?.data?.size.toString()
            binding.nownumberfmember.text = "$nownumberofmenber 人参加しています。"
        }




            collection
                    .whereEqualTo("ゲーム状況", "開始")
                    .addSnapshotListener{it,tmp->
                        if (!it?.isEmpty!!) {
                           membernumber
                                    .get()
                                    .addOnSuccessListener {
                                        var numberofpeople = it.data?.size.toString()
                                        pref.edit{
                                            putString("numberofpeople",numberofpeople)
                                        }.apply {  }
                                        startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                                    }
                        }
                    }



        binding.gotogame.setOnClickListener {
            membernumber
                    .get()
                    .addOnSuccessListener {
                        if (it.data?.size== 1 ||it.data?.size == 2 ) {
                            AlertDialog.Builder(this)
                                    .setMessage("３人必要です。")
                                    .setPositiveButton("戻る") { dialog, which ->
                                    }.show()
                        } else {
                            AlertDialog.Builder(this)
                                    .setMessage("進むとメンバーの変更が出来ません。よろしいですか？")
                                    .setPositiveButton("進む") { dialog, which ->
                                        pref.edit {
                                            putString("numberofpeople", it.data?.size.toString())
                                        }
                                        val gamesituation = hashMapOf(
                                                "ゲーム状況" to "開始"
                                        )
                                        meetingsituation.set(gamesituation)
                                        sameroomcheck.delete()
                                        startActivity(Intent(this, CenterofOnlinegameprocess::class.java))
                                    }
                                    .setNegativeButton("もどる") { dialog, which ->

                                    }.show()
                        }

                    }

        }

    }
}

 /*       if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO) ) {
            initAgoraEngineAndJoinChannel()
        }
    }

    private fun initAgoraEngineAndJoinChannel() {
        initializeAgoraEngine()
        joinChannel()
    }

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        Log.i(LOG_TAG, "checkSelfPermission $permission $requestCode")
        if (ContextCompat.checkSelfPermission(this,
                permission) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(permission),
                requestCode)
            return false
        }
        return true
    }

    override fun onUserOffline(uid: Int, reason: Int) {
        runOnUiThread { onRemoteUserLeft() }
    }

    // Listen for the onUserMuterAudio callback.
    // This callback occurs when a remote user stops sending the audio stream.
    override fun onUserMuteAudio(uid: Int, muted: Boolean) {
        runOnUiThread { onRemoteUserVoiceMuted(uid, muted)}
    }
    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(baseContext, getString(R.string.agora_app_id), mRtcEventHandler)
        } catch (e: Exception) {
            Log.e(LOG_TAG, Log.getStackTraceString(e))

            throw RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e))
        }
    }

    private fun joinChannel() {

        // Call the joinChannel method to join a channel.
        // The uid is not specified. The SDK will assign one automatically.
        mRtcEngine!!.joinChannel(token, "demoChannel1", "Extra Optional Data", 0)
    }
    private fun leaveChannel() {
        mRtcEngine!!.leaveChannel()
    }

}

  */




