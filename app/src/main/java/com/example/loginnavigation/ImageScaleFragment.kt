package com.example.loginnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ImageScaleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_image_scale, container, false)

        val imageView: ScalableImageView = view.findViewById(R.id.scalableImageView)


        Glide.with(this)
            .load("https://picsum.photos/800/600") // Random photo from internet
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)

        return view
    }
}
