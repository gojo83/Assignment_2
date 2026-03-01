package com.example.loginnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class BroadcastFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_broadcast, container, false)

        val spinner: Spinner = view.findViewById(R.id.spinnerBroadcast)
        val btnProceed: Button = view.findViewById(R.id.btnProceed)

        val options = listOf("Custom Broadcast Receiver", "System Battery Notification Receiver")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, options)
        spinner.adapter = adapter

        btnProceed.setOnClickListener {
            val selectedIndex = spinner.selectedItemPosition
            val fragment = if (selectedIndex == 0) {
                // Custom broadcast: go to input screen
                CustomBroadcastInputFragment()
            } else {
                // Battery: go directly to battery receiver
                BatteryReceiverFragment()
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
