package com.nijhoomt.ntrental.userroles.landlord.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R
import com.nijhoomt.ntrental.properties.PropertiesActivity
import kotlinx.android.synthetic.main.fragment_landlord_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_landlord_dashboard, container, false)
        val textView: TextView = view.findViewById(R.id.text_landlord_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })

        view.mcv_dashboard_property.setOnClickListener {
            startActivity(Intent(this.activity, PropertiesActivity::class.java))
        }

        return view
    }
}