package com.example.rawggames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rawggames.R
import com.example.rawggames.adapter.SearchGameAdapter
import com.example.rawggames.databinding.FragmentSearchBinding
import com.example.rawggames.viewmodel.RawgViewModel

class SearchFragment : Fragment() {
    private lateinit var query: String
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val rawgViewModel: RawgViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = rawgViewModel
            searchFragment = this@SearchFragment
            searchView.requestFocus() // Set SearchView to auto focus
        }

        setSearchGameRecyclerView()

        return binding.root
    }

    private fun setSearchGameRecyclerView() {
        binding.recyclerViewSearchedGames.apply {
            setHasFixedSize(true)
            adapter = SearchGameAdapter()
        }
    }

    fun getSearchedGames() {
        rawgViewModel.isResetSearchGame(true)
        query = binding.searchView.text.toString().lowercase().trim()
        if (query.isNotEmpty()) rawgViewModel.getSearchedGames(query)
        else Toast.makeText(requireContext(),
            resources.getString(R.string.please_enter_your_keyword),
            Toast.LENGTH_SHORT).show()
    }

    fun goToHomeFragment() {
        findNavController().navigate(R.id.action_homeSearch_to_homeFragment)
        rawgViewModel.isResetSearchGame(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}