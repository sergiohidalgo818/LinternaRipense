package com.rivas.linternaripense

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rivas.linternaripense.databinding.FragmentSecondBinding
import android.widget.ImageButton
import android.widget.TextView


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var activated = false
    private lateinit var flashButton:ImageButton
    private lateinit var movingText:TextView
    private lateinit var player: MediaPlayer

    private val binding get() = _binding!!

            override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).toggleFlashLightOn()



        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flashButton = view.findViewById(R.id.imageButton1)
        movingText = view.findViewById(R.id.textView)

        flashButton.setOnClickListener {
            if (!activated){
                flashButton.setImageResource(R.drawable.ic_pause)
                player = MediaPlayer.create(activity, R.raw.rivashimno)
                movingText.animate().y(-20000f).setDuration(180000).start()



                player.start()
                activated = true


            }
            else
            {
                flashButton.setImageResource(R.drawable.ic_play)

                movingText.animate().cancel()
                movingText.animate().translationX(0f).translationY(0f).setDuration(0)


                player.stop()
                activated = false
            }

        }

        binding.clickBandera2.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        movingText.animate().cancel()
        player.stop()

        _binding = null
    }



}