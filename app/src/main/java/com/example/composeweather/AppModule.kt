package com.example.composeweather

import com.example.composeweather.alertlist.data.WeatherAlertsDataSource
import com.example.composeweather.alertlist.io.RetrofitWeatherAlertsDataSource
import com.example.composeweather.alertlist.io.WeatherAlertsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpClient(): OkHttpClient{
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideWeatherAlertsDataSource(retrofit: Retrofit): WeatherAlertsDataSource {
        val api = retrofit.create(WeatherAlertsAPI::class.java)
        return RetrofitWeatherAlertsDataSource(api, Dispatchers.IO)
    }
}