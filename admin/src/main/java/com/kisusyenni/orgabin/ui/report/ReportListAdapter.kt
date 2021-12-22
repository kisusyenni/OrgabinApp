package com.kisusyenni.orgabin.ui.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kisusyenni.orgabin.data.source.remote.response.ReportResponseItem
import com.kisusyenni.orgabin.databinding.ItemRowReportBinding

class ReportListAdapter: RecyclerView.Adapter<ReportListAdapter.ReportViewHolder>(){

    private var listReport = ArrayList<ReportResponseItem>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setReport(report: List<ReportResponseItem>?) {
        if (report == null) return
        this.listReport.clear()
        this.listReport.addAll(report)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val itemsReportBinding = ItemRowReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(itemsReportBinding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = listReport[position]
        holder.bind(report, position)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listReport[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listReport.size

    inner class ReportViewHolder(private val binding: ItemRowReportBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(report: ReportResponseItem, position: Int) {
            with(binding) {
                tvReportDate.text = report.reportDate
                tvReportTitle.text = report.reportTitle
                tvReportDetail.text = report.reportDetail
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ReportResponseItem)
    }
}