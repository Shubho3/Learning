package com.learning.ui.activity.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.learning.R
import com.learning.apis.Const.Companion.IS_LOGIN
import com.learning.apis.Const.Companion.LOGIN
import com.learning.utills.SharedPrf

class SplashFragment : Fragment() {
    lateinit var sharedPref: SharedPrf

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_splash, container, false)
        sharedPref = SharedPrf(requireContext())
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPref.getStoredTag(LOGIN) == "true") {
                Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_mainActivity)
            } else {
                Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 3000)

        return v
    }

    companion object {


    }
}