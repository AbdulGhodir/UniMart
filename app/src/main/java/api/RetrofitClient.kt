package api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL =
        "https://gist.githubusercontent.com/Raflysaputra23/9339410b667fb09ef7d2eb19333c1173/raw/2b911ae59c60e9bd6afe03915747bdab94ca4791/"
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}