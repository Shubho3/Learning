package com.learning.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginDto(
    @Expose
    @SerializedName("result")
    val result: Result,
    @Expose
    @SerializedName("message")
    val message: String,
    @Expose
    @SerializedName("status")
    val status: String
):Serializable {
    data class Result(
        @Expose
        @SerializedName("id")
        val id: String,
        @Expose
        @SerializedName("type")
        val type: String,
        @Expose
        @SerializedName("first_name")
        val first_name: String,
        @Expose
        @SerializedName("last_name")
        val last_name: String,
        @Expose
        @SerializedName("email")
        val email: String,
        @Expose
        @SerializedName("mobile")
        val mobile: String,
        @Expose
        @SerializedName("country")
        val country: String,
        @Expose
        @SerializedName("password")
        val password: String,
        @Expose
        @SerializedName("register_id")
        val register_id: String,
        @Expose
        @SerializedName("date_time")
        val date_time: String,
        @Expose
        @SerializedName("user_name")
        val user_name: String,
        @Expose
        @SerializedName("dob")
        val dob: String,
        @Expose
        @SerializedName("image")
        val image: String,
        @Expose
        @SerializedName("social_id")
        val social_id: String,
        @Expose
        @SerializedName("status")
        val status: String,
        @Expose
        @SerializedName("token")
        val token: String,
        @Expose
        @SerializedName("expired_at")
        val expired_at: String,
        @Expose
        @SerializedName("last_login")
        val last_login: String,
        @Expose
        @SerializedName("lat")
        val lat: String,
        @Expose
        @SerializedName("lon")
        val lon: String,
        @Expose
        @SerializedName("address")
        val address: String,
        @Expose
        @SerializedName("city")
        val city: String,
        @Expose
        @SerializedName("step")
        val step: String,
        @Expose
        @SerializedName("gender")
        val gender: String,
        @Expose
        @SerializedName("establishment_no")
        val establishment_no: String,
        @Expose
        @SerializedName("lang")
        val lang: String,
        @Expose
        @SerializedName("type_id")
        val type_id: String,
        @Expose
        @SerializedName("town")
        val town: String,
        @Expose
        @SerializedName("object_name")
        val object_name: String,
        @Expose
        @SerializedName("qr_image")
        val qr_image: String,
        @Expose
        @SerializedName("qr_code")
        val qr_code: String,
        @Expose
        @SerializedName("rewards_point")
        val rewards_point: String,
        @Expose
        @SerializedName("pr_qrcode_status")
        val pr_qrcode_status: String,
        @Expose
        @SerializedName("pr_qrcode_point")
        val pr_qrcode_point: String,
        @Expose
        @SerializedName("qr_status")
        val qr_status: String
    ):Serializable
}