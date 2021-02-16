package com.example.githubapp.utilits

import com.example.githubapp.database.DBItem

interface ClickDeleteRepo {
    fun delete(item:DBItem)
}