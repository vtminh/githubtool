package com.example.framework.module

import com.example.domain.base.Constants.GITHUB_ENDPOINT
import com.example.framework.model.MRService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seagroup.ochacrm.framework.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * @author tuanminh.vu
 */

@Module
class NetworkModule {

    companion object {
        private const val GITHUB_SERVICE_NAME = "GitHubService"
        private const val REQUEST_TIME_OUT = 45L
    }

    @Provides
    @ApplicationScope
    @Named(GITHUB_SERVICE_NAME)
    fun provideCRMOKHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                chain.proceed(chain.request())
            }

        return builder.build()
    }

    @Provides
    @ApplicationScope
    @Named(GITHUB_SERVICE_NAME)
    fun provideCRMRetrofit(@Named(GITHUB_SERVICE_NAME) okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GITHUB_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideUserService(@Named(GITHUB_SERVICE_NAME) retrofit: Retrofit): MRService {
        return retrofit.create(MRService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }
}