package com.hamu.seihekijinrou.MeetingandVotingandResult

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hamu.seihekijinrou.R
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine


class CenterofMeetingandVoting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_centerof_meetingand_voting2)
    }
}
