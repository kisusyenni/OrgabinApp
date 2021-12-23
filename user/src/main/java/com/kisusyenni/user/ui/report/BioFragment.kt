package com.kisusyenni.user.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.kisusyenni.user.R
import com.kisusyenni.user.databinding.FragmentBioBinding

class BioFragment : Fragment() {

    private lateinit var fragmentBioBinding: FragmentBioBinding
    private lateinit var etReportName: EditText
    private lateinit var etReportAddress: EditText
    private lateinit var etReportTelp: EditText
    private var valid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBioBinding = FragmentBioBinding.inflate(layoutInflater, container, false)
        return fragmentBioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etReportName = fragmentBioBinding.etReportName
        etReportAddress = fragmentBioBinding.etReportAddress
        etReportTelp = fragmentBioBinding.etReportTelp

        checkAllFields(). apply {
            // go to next fragment if form data is not empty
            fragmentBioBinding.btnNextTitle.setOnClickListener {
                valid = checkAllFieldsOnClick()
                if(valid) {
                    val bioName = etReportName.text.toString()
                    val bioAddress = etReportAddress.text.toString()
                    val bioTelephone = etReportTelp.text.toString()
                    val mBundle = Bundle()

                    val mTitleFragment = TitleFragment()
                    val mFragmentManager = parentFragmentManager
                    mBundle.putString(TitleFragment.EXTRA_NAME, bioName)
                    mBundle.putString(TitleFragment.EXTRA_ADDRESS, bioAddress)
                    mBundle.putString(TitleFragment.EXTRA_TELEPHONE, bioTelephone)

                    mTitleFragment.arguments = mBundle

                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.send_report_container, mTitleFragment, TitleFragment::class.java.simpleName)
                        commit()
                    }
                }
            }
        }

    }

    private fun checkAllFields() {
        // text changed listener
        etReportName.addTextChangedListener {
            if(etReportName.text.isEmpty()) {
                etReportName.error = resources.getString(R.string.empty_value)
                valid = false
            }
        }

        etReportAddress.addTextChangedListener {
            if(etReportAddress.text.isEmpty()) {
                etReportAddress.error = resources.getString(R.string.empty_value)
                valid = false
            }
        }

        etReportTelp.addTextChangedListener {
            if(etReportTelp.text.isEmpty()) {
                etReportTelp.error = resources.getString(R.string.empty_value)
                valid = false
            }
        }
    }

    private fun checkAllFieldsOnClick(): Boolean {
        if(etReportName.text.isEmpty()) {
            etReportName.error = resources.getString(R.string.empty_value)
            return false
        }
        if(etReportAddress.text.isEmpty()) {
            etReportAddress.error = resources.getString(R.string.empty_value)
            return false
        }

        if(etReportTelp.text.isEmpty()) {
            etReportTelp.error = resources.getString(R.string.empty_value)
            return false
        }

        return true
    }
}