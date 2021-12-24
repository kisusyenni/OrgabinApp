package com.kisusyenni.user.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.kisusyenni.user.R
import com.kisusyenni.user.data.source.local.entity.UserReportEntity
import com.kisusyenni.user.data.source.local.room.UserReportDao
import com.kisusyenni.user.databinding.FragmentDescriptionBinding
import java.util.*

class DescriptionFragment : Fragment() {

    private lateinit var fragmentDescriptionBinding: FragmentDescriptionBinding
    private lateinit var etReportDescription: EditText
    private var bioName: String? = null
    private var bioAddress: String? = null
    private var bioContact: String? = null
    private var reportTitle: String? = null
    private var reportDescription: String? = null
    private var valid:Boolean = false
    private val dao = UserReportDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDescriptionBinding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return fragmentDescriptionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        etReportDescription = fragmentDescriptionBinding.etReportDescription

        if (arguments != null) {
            bioName = arguments?.getString(EXTRA_NAME)
            bioAddress = arguments?.getString(EXTRA_ADDRESS)
            bioContact = arguments?.getString(EXTRA_TELEPHONE)
            reportTitle = arguments?.getString(EXTRA_TITLE)
        }

        checkDescriptionField().apply {
            fragmentDescriptionBinding.btnFormSubmit.setOnClickListener {
                valid = checkDescriptionFieldOnClick()
                if(valid) {
                    val id = dao.generateId()
                    val date = Calendar.getInstance().time
                    reportDescription = etReportDescription.text.toString()

                    if(id != null && bioName != null && bioAddress != null && bioContact != null && reportTitle != null) {
                        val report = UserReportEntity(id, date, reportTitle, reportDescription, bioName, bioAddress, bioContact)
                        dao.sendReport(report).addOnSuccessListener {
                            Toast.makeText(requireActivity(), "New Schedule is Successfully Added", Toast.LENGTH_LONG).show()

                            activity?.onBackPressed()
                            // if successfully added return to first fragment
                            activity?.supportFragmentManager?.popBackStackImmediate()
                        }. addOnCanceledListener {
                            Toast.makeText(requireActivity(), "Failed to Add New Schedule", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }

    private fun checkDescriptionField() {
        etReportDescription.addTextChangedListener {
            if(etReportDescription.text.isEmpty()) {
                etReportDescription.error = resources.getString(R.string.empty_value)
                valid = false
            }
        }
    }
    private fun checkDescriptionFieldOnClick(): Boolean {
        if(etReportDescription.text.isEmpty()) {
            etReportDescription.error = resources.getString(R.string.empty_value)
            return false
        }
        return true
    }

    companion object {
        var EXTRA_NAME = "extra_name"
        var EXTRA_ADDRESS = "extra_address"
        var EXTRA_TELEPHONE = "extra_telephone"
        var EXTRA_TITLE = "extra_title"
    }
}