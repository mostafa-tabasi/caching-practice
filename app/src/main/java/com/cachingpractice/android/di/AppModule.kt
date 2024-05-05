package com.cachingpractice.android.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.cachingpractice.android.data.local.BeerDatabase
import com.cachingpractice.android.data.local.BeerEntity
import com.cachingpractice.android.data.remote.BeerApi
import com.cachingpractice.android.data.remote.BeerApiMockImpl
import com.cachingpractice.android.data.remote.BeerRemoteMediator
import com.cachingpractice.android.data.repository.BeerRepository
import com.cachingpractice.android.data.repository.BeerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
        return BeerApiMockImpl()
        /*
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
        */
    }

    @Provides
    @Singleton
    fun provideBeerPager(
        beerDb: BeerDatabase,
        beerApi: BeerApi,
    ): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = BeerRemoteMediator(
                beerDb,
                beerApi,
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideBeerRepository(
        beerDb: BeerDatabase,
        beerPager: Pager<Int, BeerEntity>,
    ): BeerRepository {
        return BeerRepositoryImpl(beerDb, beerPager)
    }
}