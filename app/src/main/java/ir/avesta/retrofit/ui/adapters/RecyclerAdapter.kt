package ir.avesta.retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.avesta.retrofit.data.database.remote.Person
import ir.avesta.retrofit.databinding.RecyclerViewholderBinding

class RecyclerAdapter(
    private val list: List<Person>?

) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(private val binding: RecyclerViewholderBinding)
        : RecyclerView.ViewHolder(binding.root) {

            val nameTv = binding.nameTv
            val lastNameTv = binding.lastNameTv
            val numTv = binding.numTv
            val ageTv = binding.ageTv
            val genderTv = binding.genderTv

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = RecyclerViewholderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val person = list!![position]

        holder.nameTv.text = person.firstName
        holder.lastNameTv.text = person.lastName
        holder.ageTv.text = person.age.toString()
        holder.numTv.text = person.number
        holder.genderTv.text = person.gender
    }

    override fun getItemCount(): Int = list?.size ?: 0
}