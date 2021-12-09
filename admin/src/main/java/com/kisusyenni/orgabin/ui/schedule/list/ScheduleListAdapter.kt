package com.kisusyenni.orgabin.ui.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisusyenni.orgabin.data.source.local.ScheduleEntity
import com.kisusyenni.orgabin.databinding.ItemRowScheduleBinding

class ScheduleListAdapter: RecyclerView.Adapter<ScheduleListAdapter.ScheduleViewHolder>() {

    private var listSchedule = ArrayList<ScheduleEntity>()

    fun setSchedule(schedule: List<ScheduleEntity>?) {
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
        fun bind(schedule: ScheduleEntity) {
            with(binding) {
                tvScheduleDay.text = schedule.date.toString()
                tvScheduleDate.text = schedule.date.toString()
                tvScheduleLocation.text = schedule.location.name
                tvScheduleTime.text = "${schedule.location.startTime} - ${schedule.location.endTime}"
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(DetailActivity.EXTRA_ID, movie.id)
//                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, 1)
//                    itemView.context.startActivity(intent)
//                }
            }
        }
    }

}