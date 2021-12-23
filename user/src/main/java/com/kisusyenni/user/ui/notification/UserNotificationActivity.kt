package com.kisusyenni.user.ui.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.kisusyenni.user.R
import com.kisusyenni.user.data.source.local.entity.ReportEntity
import com.kisusyenni.user.data.source.local.room.ReportDao
import com.kisusyenni.user.databinding.ActivityUserReportBinding
import com.kisusyenni.user.ui.report.UserReportActivity

class UserNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notification)
    }


}