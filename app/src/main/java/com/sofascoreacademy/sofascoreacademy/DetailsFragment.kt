package com.sofascoreacademy.sofascoreacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sofascoreacademy.model.Event

class DetailsFragment : Fragment() {
    private var pageViewModel: PageViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        val index: Int
        val events: Array<Event>
        if (arguments != null) {
            index = arguments!!.getInt(ARG_SECTION_NUMBER)
            events = arguments!!.getSerializable("events") as Array<Event>
            pageViewModel!!.setIndexAndEvents(index, events)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        val textView = root.findViewById<TextView>(R.id.section_label)
        pageViewModel!!.text.observe(this, Observer { s -> textView.text = s })
        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(index: Int, events: Array<Event?>?): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            bundle.putSerializable("events", events)
            fragment.arguments = bundle
            return fragment
        }
    }
}