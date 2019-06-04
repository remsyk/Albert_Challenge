package com.example.albert_challenge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.albert_challenge.R
import com.example.albert_challenge.model.JSONData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
//Passes in bookList from API call with OpenLibrary
class SearchListAdapter(private val bookList: List<JSONData?>?, val context: Context) : RecyclerView.Adapter<SearchListAdapter.BookViewHolder>() {

    private var imageURL = ""
    private val imageUrlBase = "http://covers.openlibrary.org/b/id/"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder (LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        if (bookList == null) {
            return 0
        } else {
            return bookList.size
        }
    }

    override fun onBindViewHolder(viewHolder: BookViewHolder, pos: Int) {
        //constructs url string for book image
        imageURL = imageUrlBase + bookList?.get(pos)?.coverI + "-L.jpg"
        //binds book image to the recycler view
        Picasso.with(context).load(imageURL).into(viewHolder.bookImageView)
        //binds book title to the recycler view
        viewHolder.titleTextView.text = bookList?.get(pos)?.title
        //binds book author to the recycler view
        viewHolder.authorTextView.text = bookList?.get(pos)?.authorName?.get(0)
    }

    // view holder that gets called in onCreateViewHolder
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImageView = view.book_image!!
        val titleTextView = view.book_title!!
        val authorTextView = view.book_author!!
    }
}