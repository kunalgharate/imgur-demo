package com.example.mvvmkotlinexample.view

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.adapters.RecyclerViewAdapter
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var context: Context

    lateinit var mainActivityViewModel: MainActivityViewModel
    private var adapter: RecyclerViewAdapter? = null
    private var isLinear = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        buttonSwitchLayout.setOnClickListener {

            mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->

                if (isLinear) {
                    // Switch to grid layout
                    val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
                    recyclerView.layoutManager = gridLayoutManager
                } else {
                    // Switch to linear layout
                    val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.layoutManager = linearLayoutManager
                }

                adapter = RecyclerViewAdapter(this,serviceSetterGetter.data,isLinear)
                recyclerView.adapter = adapter

                adapter?.notifyDataSetChanged()

                isLinear = !isLinear

            })

        }

    }

}


