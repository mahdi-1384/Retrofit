package ir.avesta.retrofit.data

import android.app.Application
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.avesta.retrofit.data.database.remote.ApiClient
import ir.avesta.retrofit.data.database.remote.ApiInterface
import ir.avesta.retrofit.data.database.remote.Person
import ir.avesta.retrofit.data.database.remote.PersonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val application: Application,
    private val mainViewModelListeners: MainViewModelListeners

    ) : ViewModel() {
    private val apiClient = ApiClient
        .getClient(ApiClient.baseUrl)?.create(ApiInterface::class.java)
    val peopleList = MutableLiveData<List<Person>?>()

    init {
        apiClient?.getData()?.enqueue(object: Callback<PersonObject> {
            override fun onResponse(call: Call<PersonObject>, response: Response<PersonObject>) {
                peopleList.value = response.body()?.people
            }

            override fun onFailure(call: Call<PersonObject>, t: Throwable) {
                mainViewModelListeners.onConnectionFailed(call, t)
            }
        })
    }

    fun openJson() {
        val intent = Intent(ACTION_VIEW, Uri
            .parse("https://filesamples.com/samples/code/json/sample4.json"))
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")

        try {
            application.startActivity(intent)

        } catch (e: Exception) {
            intent.setPackage(null)
            application.startActivity(intent)
        }
    }


    interface MainViewModelListeners {
        fun onConnectionFailed(call: Call<PersonObject>, t: Throwable)
    }
}




class MainViewModelFactory(
    private val application: Application,
    private val mainViewModelListeners: MainViewModel.MainViewModelListeners)

    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(application, mainViewModelListeners) as T
    }
}