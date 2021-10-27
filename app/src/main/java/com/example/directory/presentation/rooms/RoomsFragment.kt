package com.example.directory.presentation.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.utils.Resource
import com.example.directory.databinding.FragmentRoomsBinding
import kotlinx.android.synthetic.main.fragment_rooms.*
import org.koin.android.viewmodel.ext.android.viewModel

class RoomsFragment : Fragment() {

    private var binding: FragmentRoomsBinding? = null
    private val viewModel: RoomsViewModel by viewModel()
    private lateinit var roomsAdapter: RoomsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeProgressData()
        observeData()
    }

    private fun initViews() {
        roomsAdapter = RoomsAdapter(arrayListOf())
        rv_rooms.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv_rooms.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = roomsAdapter
        }
    }

    private fun observeData() {
        viewModel.rooms.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    roomsAdapter.updateRooms(result.result)
                }
                is Resource.Error -> {
                    binding?.tvError?.text = result.mgs
                }
                else -> ""
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