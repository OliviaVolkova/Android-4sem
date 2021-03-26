package com.example.dogs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.dogs.data.entities.DogResponse
import com.example.dogs.data.repositories.DogRepository
import com.example.dogs.data.repositories.Repository
import com.example.dogs.data.retrofit.ApiFactory
import com.example.dogs.data.room.DogsDatabase
import com.example.dogs.databinding.FragmentDogBinding
import com.example.dogs.presentation.viewmodels.DogViewModel
import com.example.dogs.presentation.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class DogFragment : Fragment() {

    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository : DogRepository
    private lateinit var viewModelFactory : ViewModelFactory
    private lateinit var viewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = Repository(ApiFactory.service, DogsDatabase.getInstance(requireContext()).dao)
        viewModelFactory = ViewModelFactory(repository, Dispatchers.IO)
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(DogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initLiveDataListener()
        initListeners()
    }

    fun initListeners(){
        with(binding){
            btnUpdatePicture.setOnClickListener {
                viewModel.updateDog()
            }
        }
    }

    private fun initLiveDataListener(){
        with(viewModel){
            getResponse().observe(viewLifecycleOwner) {
                Picasso.get()
                    .load(it.url)
                    .fit()
                    .into(binding.ivDog)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}