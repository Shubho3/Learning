package com.learning.model

import java.io.Serializable

data class LoginDto(
    val message: String,
    val result: User,
    val status: String
):  Serializable{
    data class User(
        val message: String,
        val status: String
    ):  Serializable
}