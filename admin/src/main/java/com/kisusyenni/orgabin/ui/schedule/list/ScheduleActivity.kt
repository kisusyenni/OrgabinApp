package com.kisusyenni.orgabin.ui.schedule.list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.data.source.local.room.ScheduleDao
import com.kisusyenni.orgabin.databinding.ActivityScheduleBinding
import com.kisusyenni.orgabin.ui.schedule.form.ScheduleFormActivity

class ScheduleActivity : AppCompatActivity() {
    private lateinit var activityScheduleBinding: ActivityScheduleBinding
    private val dao = ScheduleDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityScheduleBinding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(activityScheduleBinding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ScheduleListViewModel::class.java]

        val scheduleAdapter = ScheduleListAdapter(object: ScheduleListAdapter.OptionsMenuClickListener {
            override fun onOptionsMenuClicked(position: Int, id: String) {
                performOptionsMenuClick(position, id)
            }

        })
        viewModel.scheduleList.observe(this, { schedule ->
            scheduleAdapter.setSchedule(schedule)
            with(activityScheduleBinding.rvScheduleList) {
                this.layoutManager = LinearLayoutManager(this.context)
                this.setHasFixedSize(true)
                this.adapter = scheduleAdapter
            }
        })

        // Add Schedule Button Action
        activityScheduleBinding.btnMainAddSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleFormActivity::class.java)
            intent.putExtra(ScheduleFormActivity.EXTRA_ACTION,"add")
            startActivity(intent)
        }
    }

    private fun performOptionsMenuClick(position: Int, id: String) {
        val popupMenu = PopupMenu(this , activityScheduleBinding.rvScheduleList.getChildAt(position).findViewById(R.id.ib_more_row_sch))
        popupMenu.inflate(R.menu.menu_item_row_schedule)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.delete_schedule_action -> {
                        dao.deleteSchedule(id)
                        Toast.makeText(this@ScheduleActivity, "Successfully Delete Schedule", Toast.LENGTH_LONG).show()
                        return true
                    }
                    R.id.edit_schedule_action -> {
                        val intent = Intent(this@ScheduleActivity, ScheduleFormActivity::class.java)
                        intent.putExtra(ScheduleFormActivity.EXTRA_ACTION,"edit")
                        intent.putExtra(ScheduleFormActivity.EXTRA_ID,id)
                        startActivity(intent)
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}