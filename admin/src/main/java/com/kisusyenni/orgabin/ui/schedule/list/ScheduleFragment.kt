package com.kisusyenni.orgabin.ui.schedule.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.local.room.ScheduleDao
import com.kisusyenni.orgabin.databinding.FragmentScheduleBinding
import com.kisusyenni.orgabin.ui.schedule.form.ScheduleFormActivity

class ScheduleFragment : Fragment() {
    private lateinit var scheduleFragmentBinding: FragmentScheduleBinding
    private val dao = ScheduleDao()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        scheduleFragmentBinding = FragmentScheduleBinding.inflate(layoutInflater, container, false)
        return scheduleFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[ScheduleListViewModel::class.java]

            val scheduleAdapter = ScheduleListAdapter(object: ScheduleListAdapter.OptionsMenuClickListener {
                override fun onOptionsMenuClicked(position: Int, schedule: ScheduleEntity) {
                    performOptionsMenuClick(position, schedule)
                }

            })

            // observe schedule list
            viewModel.getAllScheduleList().observe(viewLifecycleOwner, { schedule ->
                scheduleAdapter.setSchedule(schedule)
                with(scheduleFragmentBinding.rvScheduleList) {
                    layoutManager = LinearLayoutManager(this.context)
                    setHasFixedSize(true)
                    adapter = scheduleAdapter
                }
            })

            // Add Schedule Button Action
            scheduleFragmentBinding.btnMainAddSchedule.setOnClickListener {
                val intent = Intent(requireActivity(), ScheduleFormActivity::class.java)
                intent.putExtra(ScheduleFormActivity.EXTRA_ACTION,"add")
                startActivity(intent)
            }
        }
    }

    private fun performOptionsMenuClick(position: Int, schedule: ScheduleEntity) {
        val popupMenu = PopupMenu(requireActivity() , scheduleFragmentBinding.rvScheduleList.getChildAt(position).findViewById(R.id.ib_more_row_sch))
        popupMenu.inflate(R.menu.menu_item_row_schedule)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.delete_schedule_action -> {
                        dao.deleteSchedule(schedule.id)
                        Toast.makeText(requireActivity(), "Successfully Delete Schedule", Toast.LENGTH_LONG).show()
                        return true
                    }
                    R.id.edit_schedule_action -> {
                        val intent = Intent(requireActivity(), ScheduleFormActivity::class.java)
                        intent.putExtra(ScheduleFormActivity.EXTRA_ACTION,"edit")
                        intent.putExtra(ScheduleFormActivity.EXTRA_SCHEDULE, schedule)
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