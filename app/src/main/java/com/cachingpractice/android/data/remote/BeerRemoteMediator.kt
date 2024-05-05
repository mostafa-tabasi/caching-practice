package com.cachingpractice.android.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cachingpractice.android.data.local.BeerDatabase
import com.cachingpractice.android.data.local.BeerEntity
import com.cachingpractice.android.data.toBeerEntity
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerDb: BeerDatabase,
    private val beerApi: BeerApi,
) : RemoteMediator<Int, BeerEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> return MediatorResult.Success(endOfPaginationReached = false)
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: run {
                        beerDb.dao.lastBeer()
                    }
                    if (lastItem == null) 1
                    else (lastItem.id / state.config.pageSize) + 1
                }
            }

            //For making the API loading more visible
            delay(2000)

            val beers = beerApi.getBeers(
                page = loadKey,
                pageSize = state.config.pageSize,
            )

            beerDb.withTransaction {
                // clear function can be implemented separately
                /*
                if (loadType == LoadType.REFRESH) {
                    beerDb.dao.clearAll()
                }
                */
                val beerEntities = beers.map { it.toBeerEntity() }
                beerDb.dao.upsertAll(beerEntities)
            }

            MediatorResult.Success(endOfPaginationReached = beers.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}