package com.cachingpractice.android.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeerDao {

    @Upsert
    suspend fun upsertAll(beers: List<BeerEntity>)

    @Query("SELECT * FROM beer_entity")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beer_entity")
    suspend fun clearAll()
}