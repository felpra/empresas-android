package com.example.empresas_android.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.empresas_android.api.ApiClient
import com.example.empresas_android.model.Enterprise
import com.example.empresas_android.model.EnterpriseResponse
import com.example.empresas_android.session.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    val isSearching = MutableLiveData<Int>()
    private val notFound = MutableLiveData<Int>()
    private val apiClient = ApiClient()
    private val sessionManager = SessionManager(context)
    val enterpriseList = MutableLiveData<List<Enterprise>>()
    var calledWithFilter: Boolean = false

    init {
        fetchEnterprise()
    }

    fun fetchEnterprise() {

        apiClient.getApiService().getEnterprise(token = sessionManager.fetchAuthToken()!!, client = sessionManager.fetchClient()!!, uid = sessionManager.fetchUid()!!)
            .enqueue(object : Callback<EnterpriseResponse> {
                override fun onFailure(call: Call<EnterpriseResponse>, t: Throwable) {
                    showNotFoundText()
                }

                override fun onResponse(call: Call<EnterpriseResponse>, response: Response<EnterpriseResponse>) {
                    enterpriseList.postValue(response.body()!!.list)
                    calledWithFilter = false
                }
            })
    }

    fun showNotFoundText(){
        notFound.postValue(View.VISIBLE)
    }

    fun onClickSearch(){
        isSearching.postValue(View.GONE)
    }


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        apiClient.getApiService().getEnterpriseByName(token = sessionManager.fetchAuthToken()!!, client = sessionManager.fetchClient()!!, uid = sessionManager.fetchUid()!!, name = s.toString())
            .enqueue(object : Callback<EnterpriseResponse> {
                override fun onFailure(call: Call<EnterpriseResponse>, t: Throwable) {
                    showNotFoundText()
                }

                override fun onResponse(call: Call<EnterpriseResponse>, response: Response<EnterpriseResponse>) {
                    enterpriseList.postValue(response.body()!!.list)
                    calledWithFilter = true
                }
            })
    }
}
