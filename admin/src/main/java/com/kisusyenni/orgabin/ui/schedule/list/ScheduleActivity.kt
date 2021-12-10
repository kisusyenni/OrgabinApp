package com.kisusyenni.orgabin.ui.schedule.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kisusyenni.orgabin.databinding.ActivityScheduleBinding
import com.kisusyenni.orgabin.ui.schedule.form.ScheduleFormActivity

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityScheduleBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(activityScheduleBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ScheduleListViewModel::class.java]

        val scheduleAdapter = ScheduleListAdapter()
        viewModel.scheduleList.observe(this, { schedule ->
            scheduleAdapter.setSchedule(schedule)
            with(activityScheduleBinding.rvScheduleList) {
                layoutManager = LinearLayoutManager(this.context)
                setHasFixedSize(true)
                adapter = scheduleAdapter
            }
        })

        // Add Schedule Button Action
        activityScheduleBinding.btnMainAddSchedule.setOnClickListener { view ->
            val intent = Intent(this, ScheduleFormActivity::class.java)
            startActivity(intent)
        }
    }
}