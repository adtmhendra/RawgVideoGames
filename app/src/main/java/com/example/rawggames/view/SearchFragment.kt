package com.example.rawggames.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rawggames.R
import com.example.rawggames.adapter.SearchGameAdapter
import com.example.rawggames.databinding.FragmentSearchBinding
import com.example.rawggames.viewmodel.RawgViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var inputMethodManager: InputMethodManager

    private lateinit var query: String
    private val disposable = CompositeDisposable()
    private val rawgViewModel: RawgViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "SearchFragment"
    }

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

            setSearchGameRecyclerView()
            edtSearch.showKeyboard()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getSearchedGamesDataTextChanged()
    }

    private fun getSearchedGamesDataTextChanged() {
        disposable.add(
            binding.edtSearch.textChanges()
                .skipInitialValue()
                .map { input -> input.toString() }
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ input ->
                    if (input.isEmpty()) rawgViewModel.isResetSearchGame(true)
                    else rawgViewModel.getSearchedGames(input.toString())
                    Log.d(TAG, input.toString())
                }, { error ->
                    Log.d(TAG, error.message.toString())
                })
        )
    }

    fun getSearchedGamesData() {
        hideKeyboard()
        rawgViewModel.isResetSearchGame(true)
        query = binding.edtSearch.text.toString().lowercase().trim()
        if (query.isEmpty()) return
        rawgViewModel.getSearchedGames(query)
    }

    private fun EditText.showKeyboard() {
        post {
            requestFocus()
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            setActionSearchToKeyboard()
        }
    }

    private fun setActionSearchToKeyboard() {
        binding.edtSearch.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                getSearchedGamesData()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSearchGameRecyclerView() {
        binding.recyclerViewSearchedGames.apply {
            setHasFixedSize(true)
            adapter = SearchGameAdapter()
            adapter?.notifyDataSetChanged()
        }
    }

    fun goToHomeFragment() {
        findNavController().navigate(R.id.action_homeSearch_to_homeFragment)
        rawgViewModel.isResetSearchGame(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        _binding = null
    }
}