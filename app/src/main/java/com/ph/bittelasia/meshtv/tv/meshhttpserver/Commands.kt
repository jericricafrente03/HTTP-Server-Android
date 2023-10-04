package com.ph.bittelasia.meshtv.tv.meshhttpserver

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

object Commands {
    fun requestUpdate(command: JSONObject) {
        CoroutineScope(Dispatchers.Main).launch {
            command.let {
                val apiKey: String = it.getString("api_key")
                val message: String = it.getString("command")

            }
        }
    }
}