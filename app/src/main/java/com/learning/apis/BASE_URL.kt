package com.learning.apis

import com.learning.apis.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://myasp-app.com/vibras/webservice/"
val interceptor = HttpLoggingInterceptor()
private val httpClient = OkHttpClient.Builder()

@Module
@InstallIn(SingletonComponent::class)
class RetrofitInstanceModule {
   @Provides
   @Singleton
   fun theRetrofitInstance() : APIService {
       val API : APIService by lazy {
           interceptor.level = HttpLoggingInterceptor.Level.BODY
           val client = httpClient.addInterceptor(interceptor)
               .connectTimeout(50, TimeUnit.SECONDS)
               .readTimeout(220, TimeUnit.SECONDS)
               .writeTimeout(220, TimeUnit.SECONDS)
               .build()
           Retrofit.Builder()
               .baseUrl(BASE_URL)
               .client(client)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(APIService::class.java)
       }
       return API
   }

}