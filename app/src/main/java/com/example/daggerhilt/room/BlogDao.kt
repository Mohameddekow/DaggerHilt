package com.example.daggerhilt.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogCacheEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun getBlog(): List<BlogCacheEntity>
}