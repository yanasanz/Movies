package com.yanasanz.movies.api

import com.yanasanz.movies.BuildConfig
import com.yanasanz.movies.utils.Constants.API_KEY
import com.yanasanz.movies.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("X-API-KEY", API_KEY)
                .addHeader("accept", "application/json")
                .build()
            return@addInterceptor chain.proceed(newRequest)
        }
        .build()

    @Singleton
    @Provides
    fun providerRetrofit(okhttp: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create()
}