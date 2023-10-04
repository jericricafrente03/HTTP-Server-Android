package com.ph.bittelasia.meshtv.tv.meshhttpserver

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.InetSocketAddress
import java.util.*
import java.util.concurrent.Executors

object HTTPConnection {
    val data = MutableLiveData<JSONObject>()
    private val mainActivity by lazy { MainActivity() }
    private var mHttpServer: HttpServer? = null
    private fun streamToString(inputStream: InputStream): String {
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }
    private fun sendResponse(httpExchange: HttpExchange, responseText: String){
        httpExchange.sendResponseHeaders(200, responseText.length.toLong())
        val os = httpExchange.responseBody
        os.write(responseText.toByteArray())
        os.close()
    }
    fun startServer(port: Int) {
        try {
            mHttpServer = HttpServer.create(InetSocketAddress(port), 0)
            mHttpServer!!.executor = Executors.newCachedThreadPool()
            mHttpServer!!.createContext("/", rootHandler)
            mHttpServer!!.createContext("/index", rootHandler)
            mHttpServer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun stopServer() {
        if (mHttpServer != null){
            mHttpServer!!.stop(0)
        }
    }
    private val rootHandler = HttpHandler { exchange ->
        runBlocking {
            when (exchange!!.requestMethod) {
                "GET" -> {
                    sendResponse(exchange, "Mesh Server")
                }
                "POST" -> {
                    val inputStream = exchange.requestBody
                    val requestBody = streamToString(inputStream)
                    val jsonBody = JSONObject(requestBody)
                    data.postValue(jsonBody)
                    Log.v("meme", "resu -> $jsonBody")
                    sendResponse(exchange, jsonBody.toString())
                }
            }
        }
    }
}