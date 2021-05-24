package com.example.empresas_android.model

import com.google.gson.annotations.SerializedName

data class Enterprise(

@SerializedName("id")
var id: Int,

@SerializedName("email_enterprise")
var email_enterprise: String,

@SerializedName("facebook")
var facebook: String,

@SerializedName("twitter")
var twitter: String,

@SerializedName("linkedin")
var linkedin: String,

@SerializedName("phone")
var phone: String,

@SerializedName("own_enterprise")
var own_enterprise: Boolean,

@SerializedName("enterprise_name")
var name: String,

@SerializedName("photo")
var photo: String,

@SerializedName("city")
var city: String,

@SerializedName("country")
var country: String,

@SerializedName("value")
var value: Int,

@SerializedName("share_price")
var share_price: Int,

@SerializedName("description")
var description: String,

@SerializedName("enterprise_type")
var eterprise_type: EnterpriseType
)




