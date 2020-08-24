package co.cyclopsapps.sitiosturisticoscolombia.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */
object ApiClient {
    //private val API_BASE_URL = "https://obscure-earth-55790.herokuapp.com"
    //private val API_BASE_URL = "http://localhost:3000"
    private val API_BASE_URL = "https://fb0608h2w9.execute-api.us-east-1.amazonaws.com"

    private var servicesApiInterface : ServicesApiInterface? = null

    fun build() : ServicesApiInterface? {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{
        @GET("/live/touristicsite")
        //@GET("/data")
        //@GET("/api/museums/")
        fun touristicSites() : Call<TouristicSiteResponse>
    }
}