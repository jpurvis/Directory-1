package com.example.directory.presentation.colleagues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.colleagues.domain.PeopleResponseItem
import com.example.directory.R
import com.example.directory.databinding.FragmentColleagueDetailsBinding
import kotlinx.android.synthetic.main.fragment_colleague_details.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ColleagueDetailsFragment : Fragment() {

    private var binding: FragmentColleagueDetailsBinding? = null
    private val args: ColleagueDetailsFragmentArgs by navArgs()
    private val viewModel: ColleaguesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColleagueDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initArgs(args.peopleResponseData)
        observeData()
    }

    private fun observeData() {
        viewModel.colleagueData.observe(viewLifecycleOwner, { data ->
            binding?.profileImage?.let { it ->
                Glide.with(requireActivity()).load(data.avatar).into(
                    it
                )
            }
            with(colleagueName) {
                setLabelEndText("${data.firstName} ${data.lastName}")
            }
            with(colleagueJobTitle) {
                setLabelEndText(data.jobTitle)
            }
            with(colleagueEmail) {
                setLabelEndText(data.email)
            }
            with(colleaguePhone) {
                setLabelEndText(data.phone)
            }
        })
    }
}