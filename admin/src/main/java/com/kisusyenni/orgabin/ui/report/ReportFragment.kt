package com.kisusyenni.orgabin.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.orgabin.data.source.local.entity.ReportEntity
import com.kisusyenni.orgabin.data.source.remote.response.ReportResponseItem
import com.kisusyenni.orgabin.databinding.FragmentReportBinding

class ReportFragment : Fragment() {
    private lateinit var fragmentReportBinding: FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentReportBinding = FragmentReportBinding.inflate(layoutInflater, container, false)
        return fragmentReportBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ReportViewModel::class.java]
            val reportListAdapter = ReportListAdapter()

            // observe reportList
            viewModel.getAllReportList().observe(viewLifecycleOwner, { reports ->

                reportListAdapter.setReport(reports)
                with(fragmentReportBinding.rvReportList) {
                    layoutManager = LinearLayoutManager(this.context)
                    setHasFixedSize(true)
                    adapter = reportListAdapter
                }

            })


            reportListAdapter.setOnItemClickCallback(object : ReportListAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ReportResponseItem) {

                    val report = data.id?.let {
                        ReportEntity(
                            it,
                            data.reportDate?.time,
                            data.reportTitle,
                            data.reportDetail,
                            data.senderName,
                            data.senderAddress,
                            data.senderContact
                        )
                    }

//                    val mReportDetailFragment = ReportDetailFragment()
//                    val mBundle = Bundle()
//                    mBundle.putParcelable(ReportDetailFragment.EXTRA_REPORT_DETAIL, report)
//                    mReportDetailFragment.arguments = mBundle
//
//                    mReportDetailFragment.show(parentFragmentManager, TAG)
                }
            })

        }
    }

    companion object {
        const val TAG = "ReportActivity"
    }
}