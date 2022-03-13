package khrushchev.ilya.wheathercouroutinesandfastadapter.repository.di

import dagger.Module
import dagger.Provides
import khrushchev.ilya.wheathercouroutinesandfastadapter.BuildConfig
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.Repository
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.RepositoryImpl
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.WheatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @Provides
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("appid", BuildConfig.FORECAST_API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideWheatherApi(retrofit: Retrofit): WheatherApi =
        retrofit.create(WheatherApi::class.java)

    @Provides
    fun provideRepository(wheatherApi: WheatherApi): Repository =
        RepositoryImpl(wheatherApi)
}