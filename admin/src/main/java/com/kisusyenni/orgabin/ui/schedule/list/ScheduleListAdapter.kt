package com.kisusyenni.orgabin.ui.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem
import com.kisusyenni.orgabin.databinding.ItemRowScheduleBinding
import com.kisusyenni.orgabin.utils.CustomDateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleListAdapter(private var optionsMenuClickListener: OptionsMenuClickListener): RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>() {

    private var listSchedule = ArrayList<ScheduleResponseItem>()
    private val customDateTimeFormatter = CustomDateTimeFormatter()

    fun setSchedule(schedule: List<ScheduleResponseItem>?) {
        if (schedule == null) return
        this.listSchedule.clear()
        this.listSchedule.addAll(schedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val itemsScheduleBinding = ItemRowScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(itemsScheduleBinding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = listSchedule[position]
        holder.bind(schedule, position)
    }

    override fun getItemCount(): Int = listSchedule.size

    inner class ScheduleViewHolder(private val binding: ItemRowScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: ScheduleResponseItem, position: Int) {
            with(binding) {
                tvScheduleDay.text = schedule.date?.let { customDateTimeFormatter.getDay(it) }
                tvScheduleDate.text = schedule.date?.let { customDateTimeFormatter.getNumberDay(it) }
                tvScheduleLocation.text = schedule.location

                val start = schedule.startTime?.let { customDateTimeFormatter.getTime(it) }
                val end = schedule.endTime?.let { customDateTimeFormatter.getTime(it) }

                tvScheduleTime.text = "$start - $end"

                ibMoreRowSch.setOnClickListener {
                    if (schedule.id !== null && schedule.date !== null && schedule.location !== null && schedule.startTime !== null && schedule.endTime !== null) {

                        val scheduleEntity = ScheduleEntity(schedule.id, schedule.date, schedule.location, schedule.startTime, schedule.endTime, true)
                        optionsMenuClickListener.onOptionsMenuClicked(position, scheduleEntity)
                    }
                }
            }
        }


    }

    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int, schedule: ScheduleEntity)
    }
}