package com.example.directory.presentation.colleagues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.utils.Resource
import com.example.directory.databinding.FragmentColleaguesBinding
import kotlinx.android.synthetic.main.fragment_colleagues.*
import org.koin.android.viewmodel.ext.android.viewModel


class ColleaguesFragment : Fragment() {

    private var binding: FragmentColleaguesBinding? = null
    private val viewModel: ColleaguesViewModel by viewModel()
    private lateinit var colleaguesAdapter: ColleaguesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColleaguesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeProgressData()
        observeData()
    }


    private fun initViews() {
        colleaguesAdapter =
            ColleaguesAdapter(arrayListOf(), ColleaguesAdapter.OnColleagueItemClick {

            })
        rv_colleagues.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv_colleagues.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = colleaguesAdapter
        }
    }

    private fun observeData() {
        viewModel.colleagues.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    colleaguesAdapter.updateColleagues(result.result)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    binding?.tvError?.text = result.mgs
                }
            }

        })
    }

    private fun observeProgressData() {
        viewModel.progress.observe(viewLifecycleOwner, { status ->
            when (status) {
                true -> showProgressBar()
                false -> hideProgressBar()
            }
        })
    }

    private fun hideProgressBar() {
        binding?.progressBar?.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

}