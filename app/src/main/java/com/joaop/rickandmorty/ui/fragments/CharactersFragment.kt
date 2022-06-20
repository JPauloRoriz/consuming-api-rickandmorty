package com.joaop.rickandmorty.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.joaop.rickandmorty.databinding.FragmentCharactersBinding
import com.joaop.rickandmorty.ui.adapter.CharacterAdapter
import com.joaop.rickandmorty.ui.extension.setOnFinsihScrollListener
import com.joaop.rickandmorty.ui.extension.showLoadingLayout
import com.joaop.rickandmorty.viewmodel.listCharacter.CharacterViewModel
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersEvent
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

        setupViews()
        setupListeners()
        setupObservers()

    }

    private fun setupViews() {
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.setHasFixedSize(false)
    }

    private fun setupListeners() {
        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
            viewModel.refreshList(true)
        }

        binding.rvCharacters.setOnFinsihScrollListener {
            viewModel.refreshList(false)
        }

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getCharacterByQuery(query) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }

    private fun setupObservers() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CharactersState.ShowLoadingScreen -> {
                    binding.rvCharacters.isVisible = !state.show
                    binding.tvList.isVisible = !state.show
                    binding.loadScreen.showLoadingLayout(state.show)
                }
                is CharactersState.OnSuccessList -> {
                    adapter.submitList(state.result)
                }
                is CharactersState.ShowLoadingPagination -> {
                    binding.loadPagination.showLoadingLayout(state.show)
                }
            }
            viewModel.eventLiveData.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is CharactersEvent.ShowAlertMessage -> {
                        Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                    }
                    is CharactersEvent.GoToDetail -> {

                    }
                    CharactersEvent.ScrollToTop -> {
                        binding.rvCharacters.layoutManager?.scrollToPosition(0)
                    }
                }
            }
        }
    }

    override fun onPause() {
        binding.loadPagination.showLoadingLayout(false)
        binding.loadScreen.showLoadingLayout(false)
        super.onPause()
    }
}

