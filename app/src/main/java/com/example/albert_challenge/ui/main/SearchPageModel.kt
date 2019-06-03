package com.example.albert_challenge.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.albert_challenge.*
import com.example.albert_challenge.adapter.RecyclerAdapter
import com.example.albert_challenge.model.JSONData
import kotlinx.android.synthetic.main.wish_list_fragment.view.*


class SearchPageModel : ViewModel() {


    var bookList: List<JSONData?>? = null


    private val _index = MutableLiveData<Int>()

     fun updateRecyclerView(v:View, newBookList: List<JSONData?>?, context: Context) {
        bookList = newBookList
        v.recyclerView.adapter = RecyclerAdapter(bookList, context)
        v.recyclerView.invalidate()
    }

     fun setupRecyclerView(v:View, context: Context) {
        v.recyclerView.layoutManager = LinearLayoutManager(context)
        v.recyclerView.recycledViewPool.setMaxRecycledViews(0,0)
    }

    fun clickListner(v:View, newBookList: List<JSONData?>?, context: Context){
        v.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(context, v.recyclerView, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                Log.i("WE MADE IT", bookList?.get(position).toString())
                val bookInfoIntent = BookInfo.newIntent(context, bookList, position)
                context.startActivity(bookInfoIntent)
            }
            override fun onItemLongClick(view: View?, position: Int) {
                Log.i("LONGPRESS", "LONGPRESS")

            }
        }))
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}


