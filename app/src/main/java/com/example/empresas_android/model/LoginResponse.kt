package com.example.empresas_android.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("access-token")
    var authToken: String,

    @SerializedName("client")
    var client: String,

    @SerializedName("uid")
    var uid: String
)
