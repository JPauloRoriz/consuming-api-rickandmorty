package com.joaop.rickandmorty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.joaop.rickandmorty.databinding.FragmentCharactersBinding
import com.joaop.rickandmorty.ui.adapter.CharacterAdapter
import com.joaop.rickandmorty.ui.extension.setOnFinsihScrollListener
import com.joaop.rickandmorty.viewmodel.listCharacter.CharacterViewModel
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersState
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel by viewModel<CharacterViewModel>()
    private val adapter by lazy { CharacterAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCharacters.adapter = adapter
        setupListeners()
        setupObservers()

    }

    private fun setupListeners() {
        binding.swipeContainer.setOnRefreshListener {
            viewModel.refreshList()
        }

        binding.rvCharacters.setOnFinsihScrollListener {
            viewModel.refreshList(true)
        }


    }

    private fun setupObservers() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CharactersState.ShowLoading -> {
                    binding.swipeContainer.isRefreshing = true
                }
                is CharactersState.OnSuccessList -> {
                    adapter.submitList(state.result)
                }
                is CharactersState.HideLoading -> {
                    binding.swipeContainer.isRefreshing = false
                }
                is CharactersState.ShowLoadingPagination -> {
                    binding.pbLoadingPagination.isVisible = state.show
                }
            }
        }
    }
}

