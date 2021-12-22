package com.kisusyenni.orgabin.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kisusyenni.orgabin.data.source.local.entity.ReportEntity
import com.kisusyenni.orgabin.databinding.FragmentReportDetailBinding

class ReportDetailFragment : BottomSheetDialogFragment() {

    private lateinit var fragmentReportDetailBinding: FragmentReportDetailBinding
    private lateinit var tvReportContentDate: TextView
    private lateinit var tvReportContentTitle: TextView
    private lateinit var tvReportContentDetail: TextView
    private lateinit var tvReportSender: TextView
    private lateinit var tvReportAddress: TextView
    private lateinit var tvReportContact: TextView

    companion object {
        var EXTRA_REPORT_DETAIL = "extra_report_detail"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentReportDetailBinding= FragmentReportDetailBinding.inflate(layoutInflater, container, false)
        return fragmentReportDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvReportContentDate = fragmentReportDetailBinding.tvReportContentDate
        tvReportContentTitle = fragmentReportDetailBinding.tvReportContentTitle
        tvReportContentDetail = fragmentReportDetailBinding.tvReportContentDetail
        tvReportSender = fragmentReportDetailBinding.tvReportSender
        tvReportAddress = fragmentReportDetailBinding.tvReportAddress
        tvReportContact = fragmentReportDetailBinding.tvReportContact

        if (arguments != null) {
            val reportDetail = arguments?.getParcelable<ReportEntity>(EXTRA_REPORT_DETAIL)
            tvReportContentTitle.text = reportDetail?.reportTitle
            tvReportContentDetail.text = reportDetail?.reportDetail
            tvReportContentDate.text = reportDetail?.reportDate
            tvReportSender.text = reportDetail?.senderName
            tvReportAddress.text = reportDetail?.senderAddress
            tvReportContact.text = reportDetail?.senderContact
        }

    }
}