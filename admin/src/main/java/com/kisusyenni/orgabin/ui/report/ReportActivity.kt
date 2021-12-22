package com.kisusyenni.orgabin.ui.report

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.orgabin.data.source.local.entity.ReportEntity
import com.kisusyenni.orgabin.data.source.remote.response.ReportResponseItem
import com.kisusyenni.orgabin.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityReportBinding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(activityReportBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ReportViewModel::class.java]
        val reportListAdapter = ReportListAdapter()

        // observe reportList
        viewModel.getAllReportList().observe(this, { reports ->

            reportListAdapter.setReport(reports)
            with(activityReportBinding.rvReportList) {
                this.layoutManager = LinearLayoutManager(this.context)
                this.setHasFixedSize(true)
                this.adapter = reportListAdapter
            }

        })

        // observe progressBar loading
//            viewModel.isLoading.observe(viewLifecycleOwner, {
//                fragmentReportListBinding.reportListProgressBar.visibility =
//                    if (it) View.VISIBLE else View.GONE
//            })

        reportListAdapter.setOnItemClickCallback(object : ReportListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ReportResponseItem) {
                val report = data.id?.let {
                    ReportEntity(
                        it,
                        data.reportDate,
                        data.reportTitle,
                        data.reportDetail,
                        data.senderName,
                        data.senderAddress,
                        data.senderContact
                    )
                }

                val mReportDetailFragment = ReportDetailFragment()
                val mBundle = Bundle()
                mBundle.putParcelable(ReportDetailFragment.EXTRA_REPORT_DETAIL, report)
                mReportDetailFragment.arguments = mBundle

                mReportDetailFragment.show(supportFragmentManager, TAG)
            }
        })

    }

    companion object {
        const val TAG = "ReportActivity"
    }
}