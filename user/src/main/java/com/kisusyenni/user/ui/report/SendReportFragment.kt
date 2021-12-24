package com.kisusyenni.user.ui.report

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kisusyenni.user.databinding.FragmentSendReportBinding

class SendReportFragment : Fragment() {

    private lateinit var fragmentSendReportBinding: FragmentSendReportBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSendReportBinding =
            FragmentSendReportBinding.inflate(layoutInflater, container, false)
        return fragmentSendReportBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSendReportBinding.navigateToReportForm.setOnClickListener {
            startActivity(Intent(requireActivity(), SendReportActivity::class.java))
        }
    }
}