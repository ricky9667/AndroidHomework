package com.example.websourcefetcher

import android.os.Bundle
import android.view.MenuInflater
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    private lateinit var httpTextView: TextView
    private lateinit var urlEditText: EditText
    private lateinit var pageSourceTextView: TextView
    private lateinit var getPageSourceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        httpTextView = findViewById(R.id.http_textview)
        httpTextView.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.http_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.http -> httpTextView.text = getString(R.string.http_text)
                    R.id.https -> httpTextView.text = getString(R.string.https_text)
                }
                true
            }
            popup.show()
        }

        urlEditText = findViewById(R.id.url_edittext)

        getPageSourceButton = findViewById(R.id.get_page_source_button)
        getPageSourceButton.setOnClickListener {
            LoaderManager.getInstance(this).restartLoader(0, null, this)
            pageSourceTextView.text = "Loading ..."
        }

        pageSourceTextView = findViewById(R.id.page_source_textview)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        val url = httpTextView.text.toString() + urlEditText.text.toString()
        return HtmlLoader(this, url)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        pageSourceTextView.text = data
    }

    override fun onLoaderReset(loader: Loader<String>) {}
}