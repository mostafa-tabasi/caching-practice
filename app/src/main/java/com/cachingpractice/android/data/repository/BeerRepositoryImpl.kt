package com.cachingpractice.android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.cachingpractice.android.data.local.BeerEntity
import com.cachingpractice.android.data.toBeer
import com.cachingpractice.android.domain.Beer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, BeerEntity>,
) : BeerRepository {
    override fun getBeerPagingFlow(): Flow<PagingData<Beer>> {
        return pager.flow
            .map { pagingData ->
                pagingData.map { beerEntity ->
                    beerEntity.toBeer()
                }
            }
    }
}