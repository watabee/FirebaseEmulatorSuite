package com.github.watabee.firebaseemulatorsuite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.watabee.firebaseemulatorsuite.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = Adapter(this)

        binding.button.setOnClickListener {
            val number = Random.nextInt()

            FirebaseFirestore.getInstance()
                .collection("numbers")
                .document(number.toString())
                .set(mapOf("number" to number))
                .addOnSuccessListener { Log.e("MainActivity", "success") }
                .addOnFailureListener { Log.e("MainActivity", "failure", it) }
        }
    }
}

private class NumberViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(inflate(parent)) {

    private val textView: TextView = itemView.findViewById(R.id.text)

    fun bind(number: Int) {
        textView.text = number.toString()
    }

    companion object {
        fun inflate(parent: ViewGroup): View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_number, parent, false)
    }
}

private class Adapter(lifecycleOwner: LifecycleOwner) : FirestoreRecyclerAdapter<Data, NumberViewHolder>(createOptions(lifecycleOwner)) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int, model: Data) {
        holder.bind(model.number)
    }

    companion object {
        fun createOptions(lifecycleOwner: LifecycleOwner) = FirestoreRecyclerOptions.Builder<Data>()
            .setLifecycleOwner(lifecycleOwner)
            .setQuery(createQuery(), Data::class.java)
            .build()

        private fun createQuery() = FirebaseFirestore.getInstance().collection("numbers")
    }
}

data class Data(val number: Int = 0)
