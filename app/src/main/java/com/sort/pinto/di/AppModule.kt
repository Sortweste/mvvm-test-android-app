package com.sort.pinto.di

import android.content.Context
import android.content.SharedPreferences
import com.sort.pinto.constants.SHARE_PREFERENCES_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*Dependency Injection for SharedPreferences*/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(
        SHARE_PREFERENCES_FILE, Context.MODE_PRIVATE)

}