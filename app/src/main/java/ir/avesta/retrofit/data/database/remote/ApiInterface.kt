package ir.avesta.retrofit.data.database.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("sample4.json")
    fun getData(): Call<PersonObject>
}