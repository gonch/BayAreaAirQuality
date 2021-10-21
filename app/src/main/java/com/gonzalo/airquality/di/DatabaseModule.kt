package com.gonzalo.airquality.di

import android.content.Context
import androidx.room.Room
import com.gonzalo.airquality.db.AirQualityDao
import com.gonzalo.airquality.db.AirQualityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: AirQualityDatabase): AirQualityDao {
        return appDatabase.airQualityDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AirQualityDatabase {
        return Room.databaseBuilder(
            appContext,
            AirQualityDatabase::class.java,
            "AirQualityDatabase"
        ).build()
    }
}