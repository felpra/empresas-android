package com.example.empresas_android.ui

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.empresas_android.Util.NetworkStatusUtil
import com.example.empresas_android.api.ApiClient
import com.example.empresas_android.model.LoginRequest
import com.example.empresas_android.model.LoginResponse
import com.example.empresas_android.session.SessionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val apiClient = ApiClient()
    val sessionManager = SessionManager(context)
    val isLoading = MutableLiveData<Int>()
    val signInError = MutableLiveData<String>()
    val startSearchActivity = MutableLiveData<Intent>()
    var emailEntryText = ""
    var passwordEntryText = ""
    var dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun onClicklogin() {
        showLoading()
        if (NetworkStatusUtil(context).getNetworkStatus()) {
            viewModelScope.launch {
                withContext(dispatcher) {
                    connectToApi()
                }
            }
        } else {
            networkNotAvailable()
        }
    }

    private fun showLoading(){
        isLoading.postValue(View.VISIBLE)
    }

    fun hideLoading(){
        isLoading.postValue(View.GONE)
    }

    private fun connectToApi(){
        apiClient.getApiService().login(LoginRequest(emailEntryText, passwordEntryText))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        signInError()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginResponse = response.headers()

                        if (loginResponse["access-token"] != null) {
                            sessionManager.saveAuthToken(loginResponse["access-token"]!!)
                            sessionManager.saveAuthClient(loginResponse["client"]!!)
                            sessionManager.saveAuthUid(loginResponse["uid"]!!)
                            hideLoading()
                            startNextActivity()
                        } else {
                            signInError()
                        }
                    }
                })
    }

    private fun startNextActivity(){
        val intent = Intent(context, SearchActivity::class.java)
        startSearchActivity.postValue(intent)
    }

    private fun signInError() {
        hideLoading()
        signInError.postValue("Credenciais informadas s??o inv??lidas, tente novamente.")
    }

    private fun networkNotAvailable() {
        hideLoading()
        signInError.postValue("Voc?? est?? offline. Verifique sua rede")
    }

}
