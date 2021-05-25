package ir.avesta.retrofit.data.database.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private var retrofit: Retrofit? = null
        const val baseUrl = "https://filesamples.com/samples/code/json/"

        fun getClient(baseUrl: String): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
            }


            return retrofit
        }
    }

}