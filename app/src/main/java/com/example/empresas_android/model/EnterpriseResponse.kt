package com.example.empresas_android.model

import com.google.gson.annotations.SerializedName

data class EnterpriseResponse(
    @SerializedName("enterprises")
    var list: List<Enterprise>
)
