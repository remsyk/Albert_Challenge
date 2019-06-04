package com.example.albert_challenge.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.albert_challenge.MainActivity
import com.example.albert_challenge.R
import com.example.albert_challenge.model.JSONData
import com.example.albert_challenge.realm.BookRealmObject
import io.realm.RealmResults


class BookWebView : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {

        const val BOOK_TITLE = "title"
        const val BASE_BOOK_URL = "https://openlibrary.org/"
        private var BOOK_URL = ""

        //declares new intent and pass in variable for that intent with a crude constructor
        fun newIntent(context: Context, bookList: List<JSONData?>?, wishList: RealmResults<BookRealmObject>?, pos: Int): Intent {
            val detailIntent = Intent(context, BookWebView::class.java)
            //this intent will pull the needed book info from the cached api data or the realm data
            if(bookList != null) {
                detailIntent.putExtra(BOOK_TITLE, bookList?.get(pos)?.title)
                detailIntent.putExtra(BOOK_URL, bookList?.get(pos)?.seed?.get(1).toString())
            }else{
                detailIntent.putExtra(BOOK_TITLE, wishList?.get(pos)?.title)
                detailIntent.putExtra(BOOK_URL, wishList?.get(pos)?.seed.toString())
            }
            return detailIntent
        }
    }
    //creates the new activity with the webview of the book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.book_detail)

        val title = intent.extras.getString(BOOK_TITLE)
        val url = BASE_BOOK_URL + intent.extras.getString(BOOK_URL)

        setTitle(title)

        webView = findViewById(R.id.detail_web_view)
        //generates a webview inside of the activity rather than in the browser
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url)
    }

    //handles back button being pressed to break out of the activity
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
