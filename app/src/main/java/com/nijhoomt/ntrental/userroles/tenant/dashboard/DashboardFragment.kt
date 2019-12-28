package com.nijhoomt.ntrental.userroles.tenant.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.model.Property
import com.nijhoomt.ntrental.properties.detail.PropertyDetailActivity
import kotlinx.android.synthetic.main.fragment_tenant_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tenant_dashboard, container, false)

        val dashboardViewModelFactory = DashboardViewModelFactory(
            activity?.application!!
        )
        dashboardViewModel =
            ViewModelProviders
                .of(this, dashboardViewModelFactory)
                .get(DashboardViewModel::class.java)

        val dashboardAllPropertiesListAdapter = DashboardAllPropertiesListAdapter()
        view.recyclerview_fortenants_allproperties.adapter = dashboardAllPropertiesListAdapter

        dashboardViewModel.listOfAllPropertiesForTenants.observe(viewLifecycleOwner, Observer {
            dashboardAllPropertiesListAdapter.submitList(it.sortedByDescending { it.id })
        })

        dashboardAllPropertiesListAdapter.setOnItemClickListener(object :
            DashboardAllPropertiesListAdapter.OnItemClickListener {
            override fun onItemClick(property: Property) {
                val intent = Intent(activity?.application!!, PropertyDetailActivity::class.java)
                intent.putExtra("SELECTED_PROPERTY", property)
                startActivity(intent)
            }
        })

        return view
    }
}