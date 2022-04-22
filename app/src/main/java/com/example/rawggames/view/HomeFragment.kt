package com.example.rawggames.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawggames.R
import com.example.rawggames.adapter.LatestGameAdapter
import com.example.rawggames.adapter.TopRatingAdapter
import com.example.rawggames.databinding.FragmentHomeBinding
import com.example.rawggames.viewmodel.RawgViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val rawgViewModel: RawgViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = rawgViewModel
            homeFragment = this@HomeFragment
        }

        setTopRatingRecyclerView()
        setLatestGameRecyclerView()

        return binding.root
    }

    private fun setTopRatingRecyclerView() {
        binding.recyclerViewTopRating.apply {
            setHasFixedSize(true)
            adapter = TopRatingAdapter()
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setLatestGameRecyclerView() {
        binding.recyclerViewLatestGame.apply {
            setHasFixedSize(true)
            adapter = LatestGameAdapter()
            layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun goToSearchFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}