package com.example.daggerhilt.di

import android.content.Context
import androidx.room.Room
import com.example.daggerhilt.room.BlogDao
import com.example.daggerhilt.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providesBlogDb(@ApplicationContext context: Context): BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesBlogDao(blogDatabase: BlogDatabase): BlogDao{
        return blogDatabase.blogDao()
    }
}