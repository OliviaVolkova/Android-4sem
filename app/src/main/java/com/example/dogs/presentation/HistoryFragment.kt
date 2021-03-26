package com.example.dogs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.dogs.data.repositories.DogRepository
import com.example.dogs.data.repositories.Repository
import com.example.dogs.data.retrofit.ApiFactory
import com.example.dogs.data.room.DogsDatabase
import com.example.dogs.databinding.FragmentHistoryBinding
import com.example.dogs.presentation.recyclerview.HistoryAdapter
import com.example.dogs.presentation.viewmodels.HistoryViewModel
import com.example.dogs.presentation.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: DogRepository
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(ApiFactory.service, DogsDatabase.getInstance(requireContext()).dao)
        viewModelFactory = ViewModelFactory(repository, Dispatchers.IO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(viewModelStore, viewModelFactory).get(HistoryViewModel::class.java)
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        initLiveData()

    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecyclerView() {
        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
    }

    private fun initLiveData() {
        viewModel.getPictures().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}