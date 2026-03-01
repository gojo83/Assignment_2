package com.example.loginnavigation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CustomBroadcastReceiverFragment : Fragment() {

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "com.example.loginnavigation.CUSTOM_BROADCAST"
        const val EXTRA_MESSAGE = "extra_message"
    }

    private lateinit var tvReceivedMessage: TextView
    private var message: String? = null

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ACTION_CUSTOM_BROADCAST) {
                val received = intent.getStringExtra(EXTRA_MESSAGE) ?: "No message"
                tvReceivedMessage.text = "Received: $received"
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_custom_broadcast_receiver, container, false)

        message = arguments?.getString("MESSAGE")
        tvReceivedMessage = view.findViewById(R.id.tvReceivedMessage)

        val btnTrigger: Button = view.findViewById(R.id.btnTriggerBroadcast)
        btnTrigger.setOnClickListener {
            val intent = Intent(ACTION_CUSTOM_BROADCAST).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            requireContext().sendBroadcast(intent)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ACTION_CUSTOM_BROADCAST)
        requireContext().registerReceiver(broadcastReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(broadcastReceiver)
    }
}
