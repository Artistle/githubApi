package com.example.githubapp.models.trueModels

data class ReposModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)