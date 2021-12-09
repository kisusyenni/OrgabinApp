package com.kisusyenni.orgabin.ui.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem
import com.kisusyenni.orgabin.databinding.ItemRowScheduleBinding
import java.text.SimpleDateFormat

class ScheduleListAdapter: RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>() {

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
        holder.bind(schedule)
    }

    override fun getItemCount(): Int = listSchedule.size

    class ScheduleViewHolder(private val binding: ItemRowScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schedule: ScheduleResponseItem) {
            with(binding) {
                tvScheduleDay.text = customizeDate("EEE", schedule.date)
                tvScheduleDate.text = customizeDate("d", schedule.date)
                tvScheduleLocation.text = schedule.location
                tvScheduleTime.text = "${customizeDate("HH:mm", schedule.startTime)} - ${customizeDate("HH:mm", schedule.endTime)}"
            }
        }
        private fun customizeDate(format:String, date:Long?): String {
            return SimpleDateFormat(format).format(date).toString()
        }
    }

}