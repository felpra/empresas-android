package com.example.empresas_android.model

import com.google.gson.annotations.SerializedName

data class EnterpriseType (
    @SerializedName("id")
    var id: Int,

    @SerializedName("enterprise_type_name")
    var type: String
)
