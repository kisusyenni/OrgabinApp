package com.kisusyenni.user.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.kisusyenni.user.R
import com.kisusyenni.user.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var titleFragmentBinding: FragmentTitleBinding
    private lateinit var etReportTitle: EditText
    private var bioName: String? = null
    private var bioAddress: String? = null
    private var bioContact: String? = null
    private var reportTitle: String? = null
    private var valid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        titleFragmentBinding = FragmentTitleBinding.inflate(layoutInflater, container, false)
        return titleFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        etReportTitle = titleFragmentBinding.etReportTitle

        if (arguments != null) {
            bioName = arguments?.getString(EXTRA_NAME)
            bioAddress = arguments?.getString(EXTRA_ADDRESS)
            bioContact = arguments?.getString(EXTRA_TELEPHONE)
        }

        checkTitleField().apply {
            // go to next fragment if form data is not empty
            titleFragmentBinding.btnNextDescription.setOnClickListener {
                valid = checkTitleFieldOnClick()
                if (valid) {
                    reportTitle = etReportTitle.text.toString()
                    val mDescriptionFragment = DescriptionFragment()
                    val mBundle = Bundle()
                    mBundle.putString(DescriptionFragment.EXTRA_NAME, bioName)
                    mBundle.putString(DescriptionFragment.EXTRA_ADDRESS, bioAddress)
                    mBundle.putString(DescriptionFragment.EXTRA_TELEPHONE, bioContact)
                    mBundle.putString(DescriptionFragment.EXTRA_TITLE, reportTitle)

                    mDescriptionFragment.arguments = mBundle

                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.send_report_container, mDescriptionFragment, DescriptionFragment::class.java.simpleName)
                        commit()

                    }
                }
            }
        }
    }

    private fun checkTitleField()  {
        etReportTitle.addTextChangedListener {
            if(etReportTitle.text.isEmpty()) {
                etReportTitle.error = resources.getString(R.string.empty_value)
                valid = false
            }
        }

    }

    private fun checkTitleFieldOnClick(): Boolean {
        if(etReportTitle.text.isEmpty()) {
            etReportTitle.error = resources.getString(R.string.empty_value)
            return false
        }
        return true
    }
    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_ADDRESS = "extra_address"
        var EXTRA_TELEPHONE = "extra_telephone"
    }
}