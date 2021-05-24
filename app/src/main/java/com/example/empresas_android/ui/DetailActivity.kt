package com.example.empresas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.empresas_android.R
import com.example.empresas_android.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var databinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setUpUi()
    }

    private fun setUpUi(){
        val image = intent.getStringExtra("photo")
        val description = intent.getStringExtra("description")
        val name = intent.getStringExtra("name")
        Glide.with(this).load(image).into(databinding.enterpriseImage)
        databinding.enterpriseText.text = description
        databinding.enterpriseName.text = name
    }
}