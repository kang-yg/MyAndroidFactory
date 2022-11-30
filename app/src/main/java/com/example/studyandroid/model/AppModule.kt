package com.example.studyandroid.model

import android.content.Context
import com.example.studyandroid.BuildConfig
import com.example.studyandroid.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

/*    // MotionLayout URL
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL*/

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context) = context

    @Provides
    fun provideBaseUrl() = Constants.LOREM_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)

    @Singleton
    @Provides
    fun provideRemoteServiceHelper(remoteServiceHelperImpl: RemoteServiceHelperImpl): RemoteServiceHelper = remoteServiceHelperImpl
}