package com.ph.bittelasia.meshtv.tv.meshhttpserver

import android.util.Log

object PassData {
    var add = arrayListOf<ItemsViewModel>()
    fun showDetails() : ArrayList<ItemsViewModel>{
        add.forEach {
            Log.v("meme","add inside -> $it")
        }
        return add
    }

}