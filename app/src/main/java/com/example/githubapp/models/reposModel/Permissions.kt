package com.example.githubapp.models.reposModel

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)