package com.example.loginnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class CustomBroadcastInputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_custom_broadcast_input, container, false)

        val etMessage: EditText = view.findViewById(R.id.etBroadcastMessage)
        val btnSend: Button = view.findViewById(R.id.btnSendBroadcast)

        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val fragment = CustomBroadcastReceiverFragment().apply {
                arguments = Bundle().apply {
                    putString("MESSAGE", message)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
