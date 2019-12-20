package com.nijhoomt.ntrental

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class SavedHomesFragment : Fragment() {

    private lateinit var savedHomesViewModel: SavedHomesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedHomesViewModel =
            ViewModelProviders.of(this).get(SavedHomesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_savedhomes, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        savedHomesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}