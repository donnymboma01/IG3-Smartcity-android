package com.example.ig3_smartcity_android.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ig3_smartcity_android.R
import com.example.ig3_smartcity_android.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    lateinit var binding: FragmentRegistrationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

}