package com.example.loginnavigation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BatteryReceiverFragment : Fragment() {

    private lateinit var tvBatteryLevel: TextView
    private lateinit var tvBatteryStatus: TextView

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPct = if (level >= 0 && scale > 0) (level * 100 / scale) else -1

                val statusCode = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val isCharging = statusCode == BatteryManager.BATTERY_STATUS_CHARGING ||
                        statusCode == BatteryManager.BATTERY_STATUS_FULL
                val statusText = if (isCharging) "Charging" else "Not Charging"

                tvBatteryLevel.text = "Battery Level: $batteryPct%"
                tvBatteryStatus.text = "Status: $statusText"
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_battery_receiver, container, false)
        tvBatteryLevel = view.findViewById(R.id.tvBatteryLevel)
        tvBatteryStatus = view.findViewById(R.id.tvBatteryStatus)
        return view
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        requireContext().registerReceiver(batteryReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(batteryReceiver)
    }
}
