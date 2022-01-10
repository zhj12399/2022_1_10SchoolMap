package com.zhj.schoolmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_zgc.setOnClickListener {
            val intent = Intent(this, ZGCActivity::class.java)
            startActivity(intent)
        }

        button_lx.setOnClickListener {
            val intent = Intent(this, LXActivity::class.java)
            startActivity(intent)
        }
    }
}