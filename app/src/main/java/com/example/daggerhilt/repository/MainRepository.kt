package com.example.daggerhilt.repository

import com.example.daggerhilt.model.Blog
import com.example.daggerhilt.retrofit.BlogRetrofit
import com.example.daggerhilt.retrofit.NetworkMapper
import com.example.daggerhilt.room.BlogDao
import com.example.daggerhilt.room.CacheMapper
import com.example.daggerhilt.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val cacheMapper: CacheMapper,
    private val blogRetrofit: BlogRetrofit,
    private val networkMapper: NetworkMapper
){

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000) //to delay the data as the progress bar is shown //@TODO don't do this in production code there's in delaying data

        try {
            val networkBlogs = blogRetrofit.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insertBlog(cacheMapper.mapToEntity(blog))
            }
            val cachedBlog = blogDao.getBlog()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))

        }catch(e: Exception){
            emit(DataState.Error(e))
        }

    }
}