package com.nijhoomt.ntrental.userroles.landlord.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nijhoomt.ntrental.R

class ToDoFragment : Fragment() {

    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toDoViewModel =
            ViewModelProviders.of(this).get(ToDoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_landlord_todo, container, false)
        val textView: TextView = root.findViewById(R.id.text_landlord_todo)
        toDoViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}