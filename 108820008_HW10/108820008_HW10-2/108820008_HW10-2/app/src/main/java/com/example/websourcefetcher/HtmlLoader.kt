package com.example.websourcefetcher

import android.content.Context
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HtmlLoader(context: Context, private val url: String) : AsyncTaskLoader<String>(context) {

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
        var result = ""

        try {
            val url = URL(this.url)
            val urlConnection = url.openConnection() as HttpURLConnection
            val inputStream = urlConnection.inputStream
            val inputStreamReader = InputStreamReader(inputStream)

            var data = inputStreamReader.read()

            while (data != -1) {
                val current = data.toChar()
                result += current
                data = inputStreamReader.read()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Check your internet connection and try again."
        }

        return result
    }
}
