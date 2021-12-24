package com.kisusyenni.orgabin.ui.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem
import com.kisusyenni.orgabin.databinding.ItemRowScheduleBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleListAdapter(private var optionsMenuClickListener: OptionsMenuClickListener): RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>() {

    private var listSchedule = ArrayList<ScheduleResponseItem>()

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
                tvScheduleDay.text = customizeDate("EEE", schedule.date)
                tvScheduleDate.text = customizeDate("d", schedule.date)
                tvScheduleLocation.text = schedule.location
                tvScheduleTime.text = "${customizeTime(schedule.startTime)} - ${customizeTime(schedule.endTime)}"

                ibMoreRowSch.setOnClickListener {
                    if (schedule.id !== null && schedule.date !== null && schedule.location !== null && schedule.startTime !== null && schedule.endTime !== null) {

                        val scheduleEntity = ScheduleEntity(schedule.id, schedule.date, schedule.location, schedule.startTime, schedule.endTime, true)
                        optionsMenuClickListener.onOptionsMenuClicked(position, scheduleEntity)
                    }
                }
            }
        }
        private fun customizeDate(format: String, date:Long?): String {
            val sdf = SimpleDateFormat(format, Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            return sdf.format(date).toString()
        }

        private fun customizeTime(date:Long?): String {
            val sdf = SimpleDateFormat("HH:mm", Locale.US)
            sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            return sdf.format(date).toString()
        }

    }

    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int, schedule: ScheduleEntity)
    }
}