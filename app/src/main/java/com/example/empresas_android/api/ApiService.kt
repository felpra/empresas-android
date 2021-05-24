package com.example.empresas_android.api

import com.example.empresas_android.model.EnterpriseResponse
import com.example.empresas_android.model.LoginRequest
import com.example.empresas_android.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(
            @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET(Constants.ENTERPRISE_URL)
    fun getEnterprise(@Header("access-token") token: String, @Header("client") client: String, @Header("uid") uid: String
    ): Call<EnterpriseResponse>

    @GET(Constants.SEARCH_BY_NAME)
    fun getEnterpriseByName(@Header("access-token") token: String, @Header("client") client: String, @Header("uid") uid: String, @Query("name") name: String
    ): Call<EnterpriseResponse>

}
