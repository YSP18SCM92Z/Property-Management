package com.nijhoomt.ntrental.tenants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R

class TenantsFragment : Fragment() {

    private lateinit var savedHomesViewModel: TenantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedHomesViewModel =
            ViewModelProviders.of(this).get(TenantsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tenants, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        savedHomesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}