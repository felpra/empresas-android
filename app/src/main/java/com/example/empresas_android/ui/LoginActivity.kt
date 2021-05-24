package com.example.empresas_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.empresas_android.R
import androidx.databinding.DataBindingUtil
import com.example.empresas_android.databinding.ActivityLoginBinding


private lateinit var viewModel: LoginActivityViewModel
private lateinit var dataBinding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setUpViewModel()
        setUpObservers()
        setUpUi()

    }

    private fun setUpUi(){
        dataBinding.accountEmailEntry.setOnFocusChangeListener { _, _ -> dataBinding.errorText.visibility = View.INVISIBLE }
        dataBinding.accountPasswordEntry.doOnTextChanged { _, _, _, _ ->
            dataBinding.errorText.visibility = View.INVISIBLE
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    private fun setUpObservers(){

        viewModel.startSearchActivity.observe(this, {
            startActivity(it)
        })

        viewModel.signInError.observe(this, {
            dataBinding.errorText.text = it
            dataBinding.errorText.visibility = View.VISIBLE
        })

        viewModel.isLoading.observe(this, {
           dataBinding.progressLoader.visibility = it
        })

    }
}