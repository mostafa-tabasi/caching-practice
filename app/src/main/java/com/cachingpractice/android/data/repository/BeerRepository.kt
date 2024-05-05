package com.cachingpractice.android.data.repository

import androidx.paging.PagingData
import com.cachingpractice.android.domain.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun getBeerPagingFlow(): Flow<PagingData<Beer>>

    suspend fun clearBeers()
}