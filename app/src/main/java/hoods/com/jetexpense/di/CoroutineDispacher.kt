package hoods.com.jetexpense.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispacherModule {

    @Provides
    @Singleton
    @IoDispatcher
    fun provideIoDispacher(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    @MainDispatcher
    fun provideMainDispacher(): CoroutineDispatcher =
        Dispatchers.Main

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher