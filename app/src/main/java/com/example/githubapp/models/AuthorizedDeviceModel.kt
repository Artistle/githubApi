package com.example.githubapp.models

data class AuthorizedDeviceModel(
    val access_token: String,
    val scope: String,
    val token_type: String
)