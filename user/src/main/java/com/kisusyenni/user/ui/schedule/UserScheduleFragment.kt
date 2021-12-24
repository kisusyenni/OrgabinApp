package com.kisusyenni.user.ui.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kisusyenni.user.databinding.FragmentUserScheduleBinding

class UserScheduleFragment : Fragment() {
    private lateinit var fragmentUserScheduleBinding: FragmentUserScheduleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentUserScheduleBinding = FragmentUserScheduleBinding.inflate(layoutInflater, container, false)
        return fragmentUserScheduleBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[UserScheduleListViewModel::class.java]

            val scheduleAdapter = UserScheduleListAdapter()

            // observe schedule list
            viewModel.getAllScheduleList().observe(viewLifecycleOwner, { schedule ->
                scheduleAdapter.setSchedule(schedule)
                with(fragmentUserScheduleBinding.rvScheduleList) {
                    layoutManager = LinearLayoutManager(this.context)
                    setHasFixedSize(true)
                    adapter = scheduleAdapter
                }
            })
        }
    }
}