package com.kisusyenni.orgabin.ui.schedule.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.orgabin.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityScheduleBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(activityScheduleBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ScheduleListViewModel::class.java]

        val scheduleAdapter = ScheduleListAdapter()
        viewModel.scheduleList.observe(this, { schedule ->
            scheduleAdapter.setSchedule(schedule)
            with(activityScheduleBinding.rvScheduleList) {
                layoutManager = LinearLayoutManager(this.context)
                setHasFixedSize(true)
                adapter = scheduleAdapter
            }
        })
    }
}