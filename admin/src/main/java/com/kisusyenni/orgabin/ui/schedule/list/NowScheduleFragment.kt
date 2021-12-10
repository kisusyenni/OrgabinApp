package com.kisusyenni.orgabin.ui.schedule.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kisusyenni.orgabin.R

class NowScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = NowScheduleFragment()
    }

    private lateinit var viewModel: ScheduleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.now_schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ScheduleListViewModel::class.java]
        // TODO: Use the ViewModel
    }

}