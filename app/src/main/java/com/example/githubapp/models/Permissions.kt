package com.example.githubapp.models

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)