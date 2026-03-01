package com.example.loginnavigation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AudioFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var tvStatus: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_audio, container, false)

        tvStatus = view.findViewById(R.id.tvAudioStatus)


        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.audio)

        mediaPlayer?.setOnCompletionListener {
            tvStatus.text = "Status: Completed"
        }

        val btnPlay: Button = view.findViewById(R.id.btnPlayAudio)
        val btnPause: Button = view.findViewById(R.id.btnPauseAudio)
        val btnStop: Button = view.findViewById(R.id.btnStopAudio)

        btnPlay.setOnClickListener {
            mediaPlayer?.let {
                if (!it.isPlaying) {
                    it.start()
                    tvStatus.text = "Status: Playing"
                }
            }
        }

        btnPause.setOnClickListener {
            mediaPlayer?.let {
                if (it.isPlaying) {
                    it.pause()
                    tvStatus.text = "Status: Paused"
                }
            }
        }

        btnStop.setOnClickListener {
            mediaPlayer?.let {
                it.pause()
                it.seekTo(0)
                tvStatus.text = "Status: Stopped"
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}