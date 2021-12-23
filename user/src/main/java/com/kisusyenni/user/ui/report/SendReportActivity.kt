package com.kisusyenni.user.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kisusyenni.user.R

class SendReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_report)

        val mFragmentManager = supportFragmentManager
        val mBioFragment = BioFragment()
        val fragment = mFragmentManager.findFragmentByTag(BioFragment::class.java.simpleName)
        if (fragment !is BioFragment) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.send_report_container, mBioFragment, BioFragment::class.java.simpleName)
                .commit()
        }
    }


}