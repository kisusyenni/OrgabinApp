package com.kisusyenni.orgabin.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityReportBinding = ActivityReportBinding.inflate(layoutInflater)

        setContentView(activityReportBinding.root)

        val reportDetailFragment = ReportDetailFragment()

        activityReportBinding.btnShow.setOnClickListener{
            reportDetailFragment.show(supportFragmentManager, TAG)
        }

    }

    companion object {
        const val TAG = "BottomReportSheetDialog"
    }
}