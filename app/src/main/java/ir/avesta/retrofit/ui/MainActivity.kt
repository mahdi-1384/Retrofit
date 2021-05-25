package ir.avesta.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import ir.avesta.retrofit.R
import ir.avesta.retrofit.data.MainViewModel
import ir.avesta.retrofit.data.MainViewModelFactory
import ir.avesta.retrofit.data.database.remote.Person
import ir.avesta.retrofit.data.database.remote.PersonObject
import ir.avesta.retrofit.databinding.ActivityMainBinding
import ir.avesta.retrofit.ui.adapters.RecyclerAdapter
import ir.avesta.retrofit.util.mToast
import retrofit2.Call

class MainActivity : AppCompatActivity(), MainViewModel.MainViewModelListeners {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.addItemDecoration(DividerItemDecoration(this, VERTICAL))


        //listeners and observers
        viewModel.peopleList.observe(this, peopleListChanged)
    }

    private val peopleListChanged = Observer<List<Person>?> {people ->
        binding.recycler.adapter = RecyclerAdapter(people)
    }

    override fun onConnectionFailed(call: Call<PersonObject>, t: Throwable) {
        mToast(t.message.toString())
    }

    fun onOpenJsonBtnClicked(view: View) = viewModel.openJson()



    private fun init() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}