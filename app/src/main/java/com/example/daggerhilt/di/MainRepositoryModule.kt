package com.example.daggerhilt.di

import com.example.daggerhilt.repository.MainRepository
import com.example.daggerhilt.retrofit.BlogRetrofit
import com.example.daggerhilt.retrofit.NetworkMapper
import com.example.daggerhilt.room.BlogDao
import com.example.daggerhilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainRepositoryModule {

    @Singleton
    @Provides
    fun providesMainRepository(
         blogDao: BlogDao,
         cacheMapper: CacheMapper,
         blogRetrofit: BlogRetrofit,
         networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, cacheMapper, blogRetrofit, networkMapper)
    }
}