package com.example.mvvmkotlinexample.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.adapters.RecyclerViewAdapter
import com.example.mvvmkotlinexample.model.ImgurImageDto
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var context: Context

    lateinit var mainActivityViewModel: MainActivityViewModel
    private var adapter: RecyclerViewAdapter? = null
    private var isLinear = true
    private var isImagesLoaded = false
    lateinit var data: List<ImgurImageDto.Data>


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.item_grid -> {
                showList(data)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


        // calling the api initial for the get data from the api and set the data to vm

        getImages()

        searchEditText.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter?.getFilter()?.filter(p0);
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        });
    }

    private fun getImages() {
        mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
         //   mainActivityViewModel.servicesLiveData.value= serviceSetterGetter;
            data= serviceSetterGetter.data
            showList(serviceSetterGetter.data)
        })
    }


    fun showList(data: List<ImgurImageDto.Data>)
    {
        if (isLinear) {

            val linearLayoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            recyclerView.layoutManager = linearLayoutManager
            // Switch to grid layout

        } else {
            // Switch to linear layout
            val gridLayoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerView.layoutManager = gridLayoutManager
        }

        adapter = RecyclerViewAdapter(this,data,isLinear)
        recyclerView.adapter = adapter

        adapter?.notifyDataSetChanged()
        isLinear = !isLinear
    }

}


