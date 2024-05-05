package com.cachingpractice.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cachingpractice.android.data.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val beerRepository: BeerRepository,
) : ViewModel() {

    val beerPagingFlow = beerRepository
        .getBeerPagingFlow()
        .cachedIn(viewModelScope)

    fun clearDb() {
        viewModelScope.launch {
            beerRepository.clearBeers()
        }
    }
}