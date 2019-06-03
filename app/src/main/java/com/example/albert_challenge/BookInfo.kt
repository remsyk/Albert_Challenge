package com.example.albert_challenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.albert_challenge.model.JSONData


class BookInfo : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {

        const val BOOK_TITLE = "title"
        const val BASE_BOOK_URL = "https://openlibrary.org/"
        private var BOOK_URL = ""

        fun newIntent(context: Context, bookList: List<JSONData?>?, pos: Int): Intent {
            val detailIntent = Intent(context, BookInfo::class.java)
            detailIntent.putExtra(BOOK_TITLE, bookList?.get(pos)?.title)
            detailIntent.putExtra(BOOK_URL, bookList?.get(pos)?.seed?.get(1).toString())
            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.book_detail)

        val title = intent.extras.getString(BOOK_TITLE)
        val url = BASE_BOOK_URL + intent.extras.getString(BOOK_URL)

        setTitle(title)

        webView = findViewById(R.id.detail_web_view)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(url)
    }

    //TODO not the best solution to this issue, when not sure why I am unable to get back effectively
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
