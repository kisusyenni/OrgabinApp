package com.kisusyenni.orgabin.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.databinding.FragmentReportDetailBinding

class ReportDetailFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentReportDetailBinding: FragmentReportDetailBinding = FragmentReportDetailBinding.inflate(layoutInflater, container, false)
        return fragmentReportDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}