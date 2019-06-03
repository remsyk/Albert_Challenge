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

class RecyclerAdapter(val bookList: List<JSONData?>?, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.BookViewHolder>() {

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
        imageURL = imageUrlBase + bookList?.get(pos)?.coverI + "-L.jpg"
        Picasso.with(context).load(imageURL).into(viewHolder.bookImageView)
        viewHolder.titleTextView.text = bookList?.get(pos)?.title
        viewHolder.authorTextView.text = bookList?.get(pos)?.authorName?.get(0)
        viewHolder.firstPublishYear.text = bookList?.get(pos)?.firstPublishYear?.toString()
    }

    // view holder
    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookImageView = view.book_image
        val titleTextView = view.book_title
        val authorTextView = view.book_author
        val firstPublishYear = view.book_year
    }
}