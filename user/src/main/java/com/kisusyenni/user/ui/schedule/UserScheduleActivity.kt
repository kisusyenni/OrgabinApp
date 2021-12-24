package com.kisusyenni.user.ui.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.user.databinding.ActivityUserScheduleBinding

class UserScheduleActivity : AppCompatActivity() {
    private lateinit var activityUserScheduleBinding: ActivityUserScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserScheduleBinding = ActivityUserScheduleBinding.inflate(layoutInflater)
        setContentView(activityUserScheduleBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UserScheduleListViewModel::class.java]

        val scheduleAdapter = UserScheduleListAdapter()

        // observe schedule list
        viewModel.getAllScheduleList().observe(this, { schedule ->
            scheduleAdapter.setSchedule(schedule)
            with(activityUserScheduleBinding.rvScheduleList) {
                this.layoutManager = LinearLayoutManager(this.context)
                this.setHasFixedSize(true)
                this.adapter = scheduleAdapter
            }
        })


    }

}