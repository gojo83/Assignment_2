package com.example.loginnavigation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        videoView = view.findViewById(R.id.videoView)

        // Load from res/raw/my_video.mp4
        // Make sure you have your mp4 file at: app/src/main/res/raw/my_video.mp4
        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.video}")
        videoView.setVideoURI(videoUri)

        // Built-in media controller (shows scrubber, play/pause on tap)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        val btnPlay: Button = view.findViewById(R.id.btnPlayVideo)
        val btnPause: Button = view.findViewById(R.id.btnPauseVideo)
        val btnStop: Button = view.findViewById(R.id.btnStopVideo)

        btnPlay.setOnClickListener {
            if (!videoView.isPlaying) videoView.start()
        }

        btnPause.setOnClickListener {
            if (videoView.isPlaying) videoView.pause()
        }

        btnStop.setOnClickListener {
            videoView.stopPlayback()
            videoView.setVideoURI(videoUri)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoView.stopPlayback()
    }
}