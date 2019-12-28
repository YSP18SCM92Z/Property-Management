package com.nijhoomt.ntrental.userroles.landlord.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        val view = inflater.inflate(R.layout.fragment_landlord_dashboard, container, false)

        view.mcv_dashboard_property.setOnClickListener {
            startActivity(Intent(this.activity, PropertiesActivity::class.java))
        }

        return view
    }
}