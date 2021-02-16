package com.example.githubapp.models.reposModel

data class ReposModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)