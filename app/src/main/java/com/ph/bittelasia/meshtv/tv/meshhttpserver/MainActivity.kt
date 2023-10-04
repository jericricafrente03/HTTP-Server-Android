package com.ph.bittelasia.meshtv.tv.meshhttpserver

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ph.bittelasia.meshtv.tv.meshhttpserver.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var incre : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            HTTPConnection.startServer(5000)
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            val adapter = Adapter(mutableListOf())
            recyclerview.adapter = adapter
            HTTPConnection.data.observe(this@MainActivity, Observer {
                val apiKey: String = it.getString("api_key")
                val message: String = it.getString("command")
                incre += 1
                adapter.addTodo(ItemsViewModel(1,incre.toString(),apiKey,message))
            })
            btnSend.setOnClickListener {
                adapter.remove()
                incre = 0
            }
        }
    }


}