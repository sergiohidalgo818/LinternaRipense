package com.rivas.linternaripense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rivas.linternaripense.databinding.FragmentSecondBinding

import android.media.MediaPlayer
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private var mMediaPlayer: MediaPlayer? = null

    private  var playing = false
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var textanim: Animation? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clickOn.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            (activity as MainActivity).flashLightsetOff()
            (activity as MainActivity).flashLightOnRoOff()

        }

        binding.playbutton.setOnClickListener {
            playing = !playing

            if (playing)
            {
                playSound()
                binding.playbutton.setImageDrawable( (activity as MainActivity).getDrawable(R.drawable.baseline_stop_circle_24))
                textanim = AnimationUtils.loadAnimation(this.context, R.anim.animationfile)
                binding.textView.setText(R.string.letra)
                binding.textView.startAnimation(textanim)
            }
            else{
                stopSound()
                binding.textView.setText("")
                binding.playbutton.setImageDrawable( (activity as MainActivity).getDrawable(R.drawable.baseline_play_circle_outline_24))

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 1. Plays the water sound
    private fun playSound() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this.context, R.raw.rivashimno)
            mMediaPlayer!!.isLooping = true
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    // 2. Pause playback
    private fun  pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    // 3. Stops playback
    private fun  stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    // 4. Destroys the MediaPlayer instance when the app is closed
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }
}