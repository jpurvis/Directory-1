package com.example.directory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.directory.databinding.FragmentColleaguesBinding


class ColleaguesFragment : Fragment() {

    private var binding:FragmentColleaguesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColleaguesBinding.inflate(inflater,container,false)
        return binding?.root
    }

}